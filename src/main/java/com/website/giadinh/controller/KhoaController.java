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

import com.website.giadinh.entity.Khoa;
import com.website.giadinh.service.KhoaService;

@Controller
@RequestMapping(value = "/admin")
public class KhoaController extends PagedListHolderCustom<Khoa> {
	@Autowired
	private KhoaService khoaService;

	@Override
	public void pagedListHolder(HttpServletRequest request, List<Khoa> list, Integer p) {
		PagedListHolder<Khoa> pagedListHolder = new PagedListHolder<Khoa>(list);
		pagedListHolder.setMaxLinkedPages(5);
		request.getSession().setAttribute("pagedListHolder", pagedListHolder);
		if (p == null) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(p);
		}
	}

	@RequestMapping(value = "/khoa", method = RequestMethod.GET)
	public String listKhoa(HttpServletRequest request, @RequestParam(required = false) Integer p, ModelMap map) {
		List<Khoa> list = khoaService.findAll();
		pagedListHolder(request, list, p);
		map.addAttribute("pageURL", "khoa");
		map.addAttribute("result", khoaService.countList());
		return "khoa";
	}

	@RequestMapping(value = "/search-khoa", method = RequestMethod.GET)
	public String searchKhoa(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam String k, ModelMap map) {
		List<Khoa> list = khoaService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("pageURL", "search-khoa");
		map.addAttribute("result", khoaService.countSearchResult(k));
		return "khoa";
	}

	@RequestMapping(value = "searchAuto-khoa", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoKhoa(HttpServletRequest request) {
		return khoaService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-khoa", method = RequestMethod.GET)
	public String addKhoa(ModelMap map) {
		map.addAttribute("khoa", new Khoa());
		map.addAttribute("add", true);
		return "khoaForm";
	}

	@RequestMapping(value = "/add-khoa", method = RequestMethod.POST)
	public String saveKhoa(@ModelAttribute("khoa") @Valid Khoa khoa, BindingResult result, ModelMap map) {
		if (khoaService.isExistKey(khoa.getMaKhoa())) {
			map.addAttribute("existKey", true);
			map.addAttribute("add", true);
			return "khoaForm";
		}

		if (result.hasErrors()) {
			map.addAttribute("add", true);
			return "khoaForm";
		}

		khoaService.add(khoa);
		map.addAttribute("success", true);
		map.addAttribute("add", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}

	@RequestMapping(value = "/edit-khoa", method = RequestMethod.GET)
	public String editKhoa(@RequestParam String maKhoa, @RequestParam Integer p, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("p", p);
		map.addAttribute("edit", true);
		return "khoaForm";
	}

	@RequestMapping(value = "/edit-khoa", method = RequestMethod.POST)
	public String updateKhoa(@ModelAttribute("khoa") @Valid Khoa khoa, BindingResult result, @RequestParam Integer p,
			ModelMap map) {
		map.addAttribute("p", p);
		if (result.hasErrors()) {
			map.addAttribute("edit", true);
			return "khoaForm";
		}
		
		khoaService.update(khoa);
		map.addAttribute("success", true);
		map.addAttribute("edit", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}

	@RequestMapping(value = "/delete-khoa", method = RequestMethod.GET)
	public String removeKhoa(@RequestParam String maKhoa, @RequestParam Integer p, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("p", p);
		if (khoaService.isExistReference(maKhoa)) {
			map.addAttribute("announceReference", true);
			return "khoaForm";
		}
		
		map.addAttribute("remove", true);
		map.addAttribute("announceRemove", true);
		return "khoaForm";
	}

	@RequestMapping(value = "/delete-khoa", method = RequestMethod.POST)
	public String deleteKhoa(@ModelAttribute("khoa") Khoa khoa, ModelMap map) {
		khoaService.delete(khoa);
		map.addAttribute("success", true);
		map.addAttribute("remove", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}
}