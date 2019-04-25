package com.website.giadinh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class KhoaController {
	@Autowired
	private KhoaService khoaService;
	
	@RequestMapping(value = "/khoa", method = RequestMethod.GET)
	public String listKhoa(ModelMap map) {
		map.addAttribute("dsKhoa", khoaService.findAll());
		map.addAttribute("result", khoaService.countList());
		return "khoa";
	}
	
	@RequestMapping(value = "/search-khoa", method = RequestMethod.GET)
	public String search(@RequestParam String k, ModelMap map) {
		List<Khoa> list = khoaService.search(k);
		if(list.isEmpty()) {
			map.addAttribute("dsKhoa", list);
			return "khoa";
		}
		map.addAttribute("search", true);
		map.addAttribute("dsKhoa", list);
		map.addAttribute("searchTerm", k);
		map.addAttribute("result", khoaService.countSearchResult(k));
		return "khoa";
	}
	
	@RequestMapping(value = "searchAuto", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAuto(HttpServletRequest request) {
		return khoaService.searchAuto(request.getParameter("term"));
	}
	
	@RequestMapping(value = "/add-khoa", method = RequestMethod.GET)
	public String addKhoa(ModelMap map) {
		map.addAttribute("khoa", new Khoa());
		map.addAttribute("edit", false);
		map.addAttribute("delete", false);
		return "khoaForm";
	}
	
	@RequestMapping(value = "/add-khoa", method = RequestMethod.POST)
	public String saveKhoa(@ModelAttribute("khoa") @Valid Khoa khoa, BindingResult result, ModelMap map) {
		if(khoaService.existKey(khoa.getMaKhoa())) {
			map.addAttribute("key", true);
			map.addAttribute("edit", false);
			map.addAttribute("delete", false);
			return "khoaForm";
		}
		
		if(result.hasErrors()) {
			map.addAttribute("edit", false);
			map.addAttribute("delete", false);
			return "khoaForm";
		}
		
		khoaService.add(khoa);
		map.addAttribute("success", true);
		map.addAttribute("insert", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}
	
	@RequestMapping(value = "/edit-khoa", method = RequestMethod.GET)
	public String editKhoa(@RequestParam String maKhoa, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("edit", true);
		map.addAttribute("delete", false);
		return "khoaForm";
	}
	
	@RequestMapping(value = "/edit-khoa", method = RequestMethod.POST)
	public String updateKhoa(@ModelAttribute("khoa") @Valid Khoa khoa, BindingResult result, ModelMap map) {
		if(result.hasErrors()) {
			map.addAttribute("edit", true);
			map.addAttribute("delete", false);
			return "khoaForm";
		}
		khoaService.update(khoa);
		map.addAttribute("success", true);
		map.addAttribute("update", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}
	
	@RequestMapping(value = "/delete-khoa", method = RequestMethod.GET)
	public String removeKhoa(@RequestParam String maKhoa, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("edit", false);
		map.addAttribute("remove", true);
		return "khoaForm";
	}
	
	@RequestMapping(value = "/delete-khoa", method = RequestMethod.POST)
	public String deleteKhoa(@ModelAttribute("khoa") Khoa khoa, ModelMap map) {
		khoaService.delete(khoa.getMaKhoa());
		map.addAttribute("success", true);
		map.addAttribute("delete", true);
		map.addAttribute("tenKhoa", khoa.getTenKhoa());
		return "khoaForm";
	}
}