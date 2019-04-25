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
		map.addAttribute("count", khoaService.count());
		return "khoa";
	}
	
	@RequestMapping(value = "/khoa", method = RequestMethod.POST)
	public String search(@RequestParam String searchTerm, ModelMap map) {
		List<Khoa> list = khoaService.search(searchTerm);
		if(list.isEmpty()) {
			map.addAttribute("noResult", true);
			return "khoa";
		}
		for(Khoa khoa : list) {
			System.out.println(khoa.getMaKhoa());
			System.out.println(khoa.getTenKhoa());
		}
		map.addAttribute("dsKhoa", list);
		map.addAttribute("searchTerm", searchTerm);
		map.addAttribute("count", khoaService.count());
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
		System.out.println(khoa.getMaKhoa());
		System.out.println(khoa.getTenKhoa());
		
		if(khoaService.existKey(khoa.getMaKhoa())) {
			System.out.println("Kiem tra:" + khoa.getMaKhoa());
			map.addAttribute("key", true);
			return "khoaForm";
		}
		
		if(result.hasErrors()) {
			return "khoaForm";
		}
		
		System.out.println("Khong kiem tra:" + khoa.getMaKhoa());
		if(khoa.getMaKhoa() != null) {
			khoaService.add(khoa);
		}
		return "redirect:/admin/khoa";
	}
	
	@RequestMapping(value = "/edit-khoa", method = RequestMethod.GET)
	public String editKhoa(@RequestParam String maKhoa, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("edit", true);
		map.addAttribute("delete", false);
		return "khoaForm";
	}
	
	@RequestMapping(value = "/edit-khoa", method = RequestMethod.POST)
	public String updateKhoa(@ModelAttribute("khoa") Khoa khoa) {
		if(khoa.getMaKhoa() != null) {
			khoaService.update(khoa);
		}
		return "redirect:/admin/khoa";
	}
	
	@RequestMapping(value = "/delete-khoa", method = RequestMethod.GET)
	public String removeKhoa(@RequestParam String maKhoa, ModelMap map) {
		map.addAttribute("khoa", khoaService.findById(maKhoa));
		map.addAttribute("edit", false);
		map.addAttribute("delete", true);
		return "khoaForm";
	}
	
	@RequestMapping(value = "/delete-khoa", method = RequestMethod.POST)
	public String deleteKhoa(@ModelAttribute("khoa") Khoa khoa) {
		System.out.println(khoa.getMaKhoa());
		System.out.println(khoa.getTenKhoa());
		if(khoa.getMaKhoa() != null) {
			khoaService.delete(khoa.getMaKhoa());
		}
		return "redirect:/admin/khoa";
	}
}