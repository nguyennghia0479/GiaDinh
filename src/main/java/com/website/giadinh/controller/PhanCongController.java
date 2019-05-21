package com.website.giadinh.controller;

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

import com.website.giadinh.entity.PhanCong;
import com.website.giadinh.service.GetListService;
import com.website.giadinh.service.PhanCongService;
import com.website.giadinh.validator.PhanCongValidator;

@Controller
@RequestMapping(value = "/admin")
public class PhanCongController extends PageController<PhanCong> {
	@Autowired
	private PhanCongService phanCongService;

	@Autowired
	private GetListService getListService;
	
	@Autowired
	private PhanCongValidator phanCongValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(phanCongValidator);
	}

	@Override
	public void pagedListHolder(HttpServletRequest request, List<PhanCong> list, Integer p) {
		PagedListHolder<PhanCong> pagedListHolder = new PagedListHolder<PhanCong>(list);
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
		return "phan-cong?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "phan-cong?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/phan-cong", method = RequestMethod.GET)
	public String getPhanCongList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "phan-cong");
		if (k == null) {
			List<PhanCong> list = phanCongService.findAll();
			pagedListHolder(request, list, p);
			map.addAttribute("result", phanCongService.countList());
			return "phanCong";
		}
		List<PhanCong> list = phanCongService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", phanCongService.countSearchResult(k));
		return "phanCong";
	}

	@RequestMapping(value = "/searchAuto-phan-cong", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoPhanCong(HttpServletRequest request) {
		return phanCongService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-phan-cong", method = RequestMethod.GET)
	public String addPhanCong(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("phanCong", new PhanCong());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "phanCongForm";
	}

	@RequestMapping(value = "/add-phan-cong", method = RequestMethod.POST)
	public String savePhanCong(@ModelAttribute("phanCong") @Valid PhanCong phanCong, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		System.out.println(phanCong.getMaPC());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "phanCongForm";
		}

		phanCongService.add(phanCong);
		map.addAttribute("success", true);
		return "phanCongForm";
	}

	@RequestMapping(value = { "/edit-phan-cong", "/delete-phan-cong" }, method = RequestMethod.GET)
	public String getPhanCong(@RequestParam Long maPC, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		map.addAttribute("phanCong", phanCongService.findById(maPC));
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-phan-cong")) {
			map.addAttribute("edit", true);
		} else {
//			if(phanCongService.isExistReference(maPC)) {
//				map.addAttribute("announceReference", true);
//				return "phanCongForm";
//			}

			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "phanCongForm";
	}

	@RequestMapping(value = "/edit-phan-cong", method = RequestMethod.POST)
	public String updatePhanCong(@ModelAttribute("phanCong") @Valid PhanCong phanCong, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "phanCongForm";
		}

		phanCongService.update(phanCong);
		map.addAttribute("success", true);
		return "phanCongForm";
	}

	@RequestMapping(value = "/delete-phan-cong", method = RequestMethod.POST)
	public String deletePhanCong(@ModelAttribute("phanCong") PhanCong phanCong, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("remove", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if(k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		
		phanCongService.delete(phanCong);
		map.addAttribute("success", true);
		return "phanCongForm";
	}
	
	@ModelAttribute("lopHocList")
	private Map<String, String> getLopHocList() {
		return getListService.getLopHocList();
	}
	
	@ModelAttribute("giangVienList")
	private Map<String, String> getGiangVienList() {
		return getListService.getGiangVienList();
	}
	
	@ModelAttribute("monHocList")
	private Map<String, String> getMonHocList() {
		return getListService.getMonHocList();
	}
}