package com.website.giadinh.controller;

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

import com.website.giadinh.entity.SinhVien;
import com.website.giadinh.entity.TaiKhoan;
import com.website.giadinh.service.GetListService;
import com.website.giadinh.service.SinhVienService;
import com.website.giadinh.service.TaiKhoanService;
import com.website.giadinh.validator.SinhVienValidator;

@Controller
@RequestMapping(value = "/admin")
public class SinhVienController extends PageController<SinhVien> {
	@Autowired
	private SinhVienService sinhVienService;

	@Autowired
	private SinhVienValidator sinhVienValidator;

	@Autowired
	private GetListService getListService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(sinhVienValidator);
	}

	@Autowired
	private TaiKhoanService taiKhoanService;

	@Override
	public void pagedListHolder(HttpServletRequest request, List<SinhVien> list, Integer p) {
		PagedListHolder<SinhVien> pagedListHolder = new PagedListHolder<SinhVien>(list);
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
		return "sinh-vien?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "sinh-vien?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/sinh-vien", method = RequestMethod.GET)
	public String getSinhVienList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "sinh-vien");
		if (k == null) {
			List<SinhVien> list = sinhVienService.findAll();
			pagedListHolder(request, list, p);
			map.addAttribute("result", sinhVienService.countList());
			return "sinhVien";
		}
		List<SinhVien> list = sinhVienService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", sinhVienService.countSearchResult(k));
		return "sinhVien";
	}

	@RequestMapping(value = "/searchAuto-sinh-vien", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoSinhVien(HttpServletRequest request) {
		return sinhVienService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-sinh-vien", method = RequestMethod.GET)
	public String addSinhVien(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("sinhVien", new SinhVien());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "sinhVienForm";
	}

	@RequestMapping(value = "/add-sinh-vien", method = RequestMethod.POST)
	public String saveSinhVien(@ModelAttribute("sinhVien") @Valid SinhVien sinhVien, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map,
			@RequestParam CommonsMultipartFile image) {
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (image.isEmpty()) {
			map.addAttribute("error", true);
			map.addAttribute("message", "Bạn chưa chọn hình ảnh");
			return "sinhVienForm";
		}

		if (!contentTypes.contains(image.getContentType())) {
			map.addAttribute("error", true);
			map.addAttribute("message", "Bạn hãy chọn file hình ảnh png hoặc jpg");
			return "sinhVienForm";
		}

		if (result.hasErrors()) {
			return "sinhVienForm";
		}

		sinhVien.setHinhAnh(image.getBytes());
		map.addAttribute("img", sinhVien);
		sinhVienService.add(sinhVien);

		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setMaTK(sinhVien.getMaSV());
		taiKhoan.setMatKhau(sinhVien.getMaSV());
		taiKhoan.setQuyen(3);
		taiKhoanService.add(taiKhoan);

		map.addAttribute("success", true);
		map.addAttribute("hoTen", sinhVien.getHoTen());
		return "sinhVienForm";
	}

	@RequestMapping(value = { "/edit-sinh-vien", "/delete-sinh-vien" }, method = RequestMethod.GET)
	public String getSinhVien(@RequestParam String maSV, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		SinhVien sinhVien = sinhVienService.findById(maSV);
		map.addAttribute("img", sinhVien);
		map.addAttribute("sinhVien", sinhVien);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-sinh-vien")) {
			map.addAttribute("edit", true);
			map.addAttribute("mode", "edit");
		} else {
			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "sinhVienForm";
	}

	@RequestMapping(value = "/edit-sinh-vien", method = RequestMethod.POST)
	public String updateSinhVien(@ModelAttribute("sinhVien") @Valid SinhVien sinhVien, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map,
			@RequestParam CommonsMultipartFile image) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (image.isEmpty()) {
			SinhVien sv = sinhVienService.findById(sinhVien.getMaSV());
			sinhVien.setHinhAnh(sv.getHinhAnh());
		} else {
			if (!contentTypes.contains(image.getContentType())) {
				map.addAttribute("error", true);
				map.addAttribute("message", "Bạn hãy chọn file hình ảnh png hoặc jpg");
				return "sinhVienForm";
			}
			sinhVien.setHinhAnh(image.getBytes());
		}

		if (result.hasErrors()) {
			return "sinhVienForm";
		}

		map.addAttribute("img", sinhVien);
		sinhVienService.update(sinhVien);
		map.addAttribute("success", true);
		map.addAttribute("hoTen", sinhVien.getHoTen());
		return "sinhVienForm";
	}

	@RequestMapping(value = "/delete-sinh-vien", method = RequestMethod.POST)
	public String deleteSinhVien(@ModelAttribute("sinhVien") SinhVien sinhVien, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		TaiKhoan taiKhoan = taiKhoanService.findById(sinhVien.getMaSV());
		map.addAttribute("pageURL", getReturnPage(p));
		if(k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		
		sinhVienService.delete(sinhVien);
		taiKhoanService.delete(taiKhoan);
		map.addAttribute("success", true);
		map.addAttribute("remove", true);
		map.addAttribute("hoTen", sinhVien.getHoTen());
		return "sinhVienForm";
	}

	private List<String> contentTypes = Arrays.asList("image/png", "image/jpeg");

	@ModelAttribute("noiSinhList")
	private Map<String, String> getNoiSinhList() {
		return getListService.getNoiSinhList();
	}

	@ModelAttribute("lopHocList")
	private Map<String, String> getLopHocList() {
		return getListService.getLopHocList();
	}
}