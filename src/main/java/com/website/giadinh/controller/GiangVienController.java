package com.website.giadinh.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.website.giadinh.entity.GiangVien;
import com.website.giadinh.entity.TaiKhoan;
import com.website.giadinh.service.GetListService;
import com.website.giadinh.service.GiangVienService;
import com.website.giadinh.service.TaiKhoanService;
import com.website.giadinh.validator.GiangVienValidator;

@Controller
@RequestMapping(value = "/admin")
public class GiangVienController extends PageController<GiangVien> {
	@Autowired
	private GiangVienService giangVienService;

	@Autowired
	private GiangVienValidator giangVienValidator;

	@Autowired
	private GetListService getListService;

	@Autowired
	private TaiKhoanService taiKhoanService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(giangVienValidator);
	}

	@Override
	public void pagedListHolder(HttpServletRequest request, List<GiangVien> list, Integer p) {
		PagedListHolder<GiangVien> pagedListHolder = new PagedListHolder<GiangVien>(list);
		pagedListHolder.setMaxLinkedPages(5);
		request.getSession().setAttribute("pagedListHolder", pagedListHolder);
		if (p == null) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(p);
		}
	}

	@Override
	public String getReturnPage(Integer p) {
		return "giang-vien?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "giang-vien?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/giang-vien", method = RequestMethod.GET)
	public String getGiangVienList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "giang-vien");
		if (k == null) {
			List<GiangVien> list = giangVienService.findAll();
			showImage(list);
			pagedListHolder(request, list, p);
			map.addAttribute("result", giangVienService.countList());
			return "giangVien";
		}
		List<GiangVien> list = giangVienService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", giangVienService.countSearchResult(k));
		return "giangVien";
	}

	@RequestMapping(value = "/searchAuto-giang-vien", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoGiangVien(HttpServletRequest request) {
		return giangVienService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-giang-vien", method = RequestMethod.GET)
	public String addGiangVien(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("giangVien", new GiangVien());
		map.addAttribute("pageURL", getReturnPage(p));
		map.addAttribute("add", true);
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "giangVienForm";
	}

	@RequestMapping(value = "/add-giang-vien", method = RequestMethod.POST)
	public String saveGiangVien(@ModelAttribute("giangVien") @Valid GiangVien giangVien, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map,
			@RequestParam CommonsMultipartFile image) {
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (image.isEmpty()) {
			map.addAttribute("errorImg", true);
			map.addAttribute("message", "Bạn chưa chọn hình ảnh");
			return "giangVienForm";
		}

		if (!contentTypes.contains(image.getContentType())) {
			map.addAttribute("errorImg", true);
			map.addAttribute("message", "Ban hãy chọn file ảnh png hoặc jpg");
			return "giangVienForm";
		}
		
		if (giangVienService.isExistKey(giangVien.getMaGV())) {
			map.addAttribute("existKey", true);
			return "giangVienForm";
		}

		if (result.hasErrors()) {
			return "giangVienForm";
		}

		giangVien.setHinhAnh(image.getBytes());
		showImg(giangVien);
		map.addAttribute("img", giangVien);
		giangVienService.add(giangVien);

		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setMaTK(giangVien.getMaGV());
		taiKhoan.setMatKhau(giangVien.getMaGV());
		taiKhoan.setQuyen("TEACHER");
		taiKhoanService.add(taiKhoan);

		map.addAttribute("success", true);
		map.addAttribute("hoTen", giangVien.getHoTen());
		return "giangVienForm";
	}

	@RequestMapping(value = { "/edit-giang-vien", "/delete-giang-vien" }, method = RequestMethod.GET)
	public String getGiangVien(@RequestParam String maGV, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		GiangVien giangVien = giangVienService.findById(maGV);
		showImg(giangVien);
		map.addAttribute("img", giangVien);
		map.addAttribute("giangVien", giangVien);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-giang-vien")) {
			map.addAttribute("edit", true);
		} else {
//			if(giangVienService.isExistReference(maGV)) {
//				map.addAttribute("announceReference", true);
//				return "giangVienForm";
//			}

			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "giangVienForm";
	}

	@RequestMapping(value = "/edit-giang-vien", method = RequestMethod.POST)
	public String updateGiangVien(@ModelAttribute("giangVien") @Valid GiangVien giangVien, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map,
			@RequestParam CommonsMultipartFile image) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (image.isEmpty()) {
			GiangVien gv = giangVienService.findById(giangVien.getMaGV());
			giangVien.setHinhAnh(gv.getHinhAnh());
		} else {
			if (!contentTypes.contains(image.getContentType())) {
				map.addAttribute("errorImg", true);
				map.addAttribute("message", "Ban hãy chọn file ảnh png hoặc jpg");
				return "giangVienForm";
			}
			giangVien.setHinhAnh(image.getBytes());
			showImg(giangVien);
		}

		if (result.hasErrors()) {
			return "giangVienForm";
		}

		map.addAttribute("img", giangVien);
		giangVienService.update(giangVien);
		map.addAttribute("success", true);
		map.addAttribute("hoTen", giangVien.getHoTen());
		return "giangVienForm";
	}

	@RequestMapping(value = "/delete-giang-vien", method = RequestMethod.POST)
	public String deleteGiangVien(@ModelAttribute("giangVien") GiangVien giangVien, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		TaiKhoan taiKhoan = taiKhoanService.findById(giangVien.getMaGV());
		System.out.println(giangVien.getMaGV());
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		giangVienService.delete(giangVien);
		taiKhoanService.delete(taiKhoan);
		map.addAttribute("success", true);
		map.addAttribute("remove", true);
		map.addAttribute("hoTen", giangVien.getHoTen());
		return "giangVienForm";
	}

	private void showImage(List<GiangVien> list) {
		for (GiangVien gv : list) {
			try {
				byte[] encodeBase64 = gv.getHinhAnh();
				String base64 = new String(encodeBase64, "UTF-8");
				gv.setBase64(base64);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private void showImg(GiangVien gv) {
		try {
			byte[] encodeBase64 = gv.getHinhAnh();
			String base64 = new String(encodeBase64, "UTF-8");
			gv.setBase64(base64);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private List<String> contentTypes = Arrays.asList("image/png", "image/jpeg");

	@ModelAttribute("trinhDoList")
	private Map<String, String> getTrinhDoList() {
		return getListService.getTrinhDoList();
	}

	@ModelAttribute("noiSinhList")
	private Map<String, String> getNoiSinhList() {
		return getListService.getNoiSinhList();
	}

	@ModelAttribute("khoaList")
	private Map<String, String> getKhoaList() {
		return getListService.getKhoaList();
	}
}