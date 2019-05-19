package com.website.giadinh.controller;

import java.util.List;

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

import com.website.giadinh.entity.PhongHoc;
import com.website.giadinh.service.PhongHocService;
import com.website.giadinh.validator.PhongHocValidator;

@Controller
@RequestMapping(value = "/admin")
public class PhongHocController extends PageController<PhongHoc> {
	@Autowired
	private PhongHocService phongHocService;
	
	@Autowired
	private PhongHocValidator phongHocValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(phongHocValidator);
	}

	@Override
	public void pagedListHolder(HttpServletRequest request, List<PhongHoc> list, Integer p) {
		PagedListHolder<PhongHoc> pagedListHolder = new PagedListHolder<PhongHoc>(list);
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
		return "phong-hoc?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "phong-hoc?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/phong-hoc", method = RequestMethod.GET)
	public String getPhongHocList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "phong-hoc");
		if (k == null) {
			List<PhongHoc> list = phongHocService.findAll();
			pagedListHolder(request, list, p);
			map.addAttribute("result", phongHocService.countList());
			return "phongHoc";
		}
		List<PhongHoc> list = phongHocService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", phongHocService.countSearchResult(k));
		return "phongHoc";
	}

	@RequestMapping(value = "/searchAuto-phong-hoc", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoPhongHoc(HttpServletRequest request) {
		return phongHocService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-phong-hoc", method = RequestMethod.GET)
	public String addPhongHoc(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("phongHoc", new PhongHoc());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "phongHocForm";
	}

	@RequestMapping(value = "/add-phong-hoc", method = RequestMethod.POST)
	public String savePhongHoc(@ModelAttribute("phongHoc") @Valid PhongHoc phongHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "phongHocForm";
		}

		phongHocService.add(phongHoc);
		map.addAttribute("success", true);
		map.addAttribute("maPH", phongHoc.getMaPH());
		return "phongHocForm";
	}

	@RequestMapping(value = { "/edit-phong-hoc", "/delete-phong-hoc" }, method = RequestMethod.GET)
	public String getPhongHoc(@RequestParam String maPH, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		map.addAttribute("phongHoc", phongHocService.findById(maPH));
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-phong-hoc")) {
			map.addAttribute("edit", true);
			map.addAttribute("mode", "edit");
		} else {
//			if (phongHocService.isExistReference(maPH)) {
//				map.addAttribute("announceReference", true);
//				return "phongHocForm";
//			}

			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "phongHocForm";
	}

	@RequestMapping(value = "/edit-phong-hoc", method = RequestMethod.POST)
	public String updatePhongHoc(@ModelAttribute("phongHoc") @Valid PhongHoc phongHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "phongHocForm";
		}

		phongHocService.update(phongHoc);
		map.addAttribute("success", true);
		map.addAttribute("maPH", phongHoc.getMaPH());
		return "phongHocForm";
	}

	@RequestMapping(value = "/delete-phong-hoc", method = RequestMethod.POST)
	public String deletePhongHoc(@ModelAttribute("phongHoc") PhongHoc phongHoc, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("remove", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		phongHocService.delete(phongHoc);
		map.addAttribute("success", true);
		map.addAttribute("maPH", phongHoc.getMaPH());
		return "phongHocForm";
	}
}