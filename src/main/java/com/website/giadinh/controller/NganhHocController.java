package com.website.giadinh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.website.giadinh.entity.NganhHoc;
import com.website.giadinh.service.KhoaService;
import com.website.giadinh.service.NganhHocService;

@Controller
@RequestMapping(value = "/admin")
public class NganhHocController extends PagedListHolderCustom<NganhHoc> {
	@Autowired
	private NganhHocService nganhHocService;

	@Autowired
	private KhoaService khoaService;

	@Override
	public void pagedListHolder(HttpServletRequest request, List<NganhHoc> list, Integer p) {
		PagedListHolder<NganhHoc> pagedListHolder = new PagedListHolder<NganhHoc>(list);
		pagedListHolder.setMaxLinkedPages(5);
		request.getSession().setAttribute("pagedListHolder", pagedListHolder);
		if (p == null) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(p);
		}
	}

	@RequestMapping(value = "/nganh-hoc", method = RequestMethod.GET)
	public String listNganhHoc(HttpServletRequest request, @RequestParam(required = false) Integer p, ModelMap map) {
		List<NganhHoc> list = nganhHocService.findAll();
		pagedListHolder(request, list, p);
		map.addAttribute("pageURL", "nganh-hoc");
		map.addAttribute("result", nganhHocService.countList());
		return "nganhHoc";
	}

	@RequestMapping(value = "/search-nganh-hoc", method = RequestMethod.GET)
	public String searchNganhHoc(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam String k, ModelMap map) {
		List<NganhHoc> list = nganhHocService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("pageURL", "search-nganh-hoc");
		map.addAttribute("result", nganhHocService.countSearchResult(k));
		return "nganhHoc";
	}

	@RequestMapping(value = "/searchAuto-nganh-hoc", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoNganhHoc(HttpServletRequest request) {
		return nganhHocService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-nganh-hoc", method = RequestMethod.GET)
	public String addNganhHoc(ModelMap map) {
		map.addAttribute("nganhHoc", new NganhHoc());
		map.addAttribute("khoa", khoaService.findAll());
		map.addAttribute("add", true);
		return "nganhHocForm";
	}

	@RequestMapping(value = "/add-nganh-hoc", method = RequestMethod.POST)
	public String saveNganhHoc(@ModelAttribute("nganhHoc") @Valid NganhHoc nganhHoc, BindingResult result,
			ModelMap map) {
		if (nganhHocService.isExistKey(nganhHoc.getMaNganh())) {
			map.addAttribute("khoa", khoaService.findAll());
			map.addAttribute("add", true);
			map.addAttribute("existKey", true);
			return "nganhHocForm";
		}

		if (result.hasErrors()) {
			map.addAttribute("khoa", khoaService.findAll());
			map.addAttribute("add", true);
			return "nganhHocForm";
		}

		nganhHocService.add(nganhHoc);
		map.addAttribute("success", true);
		map.addAttribute("add", true);
		map.addAttribute("tenNganh", nganhHoc.getTenNganh());
		return "nganhHocForm";
	}

	@RequestMapping(value = "/edit-nganh-hoc", method = RequestMethod.GET)
	public String editNganhHoc(@RequestParam String maNganh, @RequestParam Integer p, ModelMap map) {
		map.addAttribute("nganhHoc", nganhHocService.findById(maNganh));
		map.addAttribute("p", p);
		map.addAttribute("khoa", khoaService.findAll());
		map.addAttribute("edit", true);
		return "nganhHocForm";
	}

	@RequestMapping(value = "/edit-nganh-hoc", method = RequestMethod.POST)
	public String updateNganhHoc(@ModelAttribute("nganhHoc") @Valid NganhHoc nganhHoc, BindingResult result,
			@RequestParam Integer p, ModelMap map) {
		map.addAttribute("p", p);
		if(result.hasErrors()) {
			map.addAttribute("khoa", khoaService.findAll());
			map.addAttribute("edit", true);
			return "nganhHocForm";
		}
		
		nganhHocService.update(nganhHoc);
		map.addAttribute("success", true);
		map.addAttribute("edit", true);
		map.addAttribute("tenNganh", nganhHoc.getTenNganh());
		return "nganhHocForm";
	}

	@RequestMapping(value = "/delete-nganh-hoc", method = RequestMethod.GET)
	public String removeNganhHoc(@RequestParam String maNganh, @RequestParam Integer p, ModelMap map) {
		map.addAttribute("nganhHoc", nganhHocService.findById(maNganh));
		map.addAttribute("p", p);
		map.addAttribute("khoa", khoaService.findAll());
//		if(nganhHocService.isExistReference(maNganh)) {
//			map.addAttribute("announceReference", true);
//			return "nganhHocForm";
//		}
		
		map.addAttribute("remove", true);
		map.addAttribute("announceRemove", true);
		return "nganhHocForm";
	}
	
	@RequestMapping(value = "/delete-nganh-hoc", method = RequestMethod.POST)
	public String deleteNganhHoc(@ModelAttribute("nganhHoc") NganhHoc nganhHoc, ModelMap map) {
		nganhHocService.delete(nganhHoc);
		map.addAttribute("success", true);
		map.addAttribute("remove", true);
		map.addAttribute("tenNganh", nganhHoc.getTenNganh());
		return "nganhHocForm";
	}
}