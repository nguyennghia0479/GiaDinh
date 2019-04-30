package com.website.giadinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.website.giadinh.service.NganhHocService;

@Controller
@RequestMapping(value = "/admin")
public class NganhHocController {
	@Autowired
	private NganhHocService nganhHocService;
	
	@RequestMapping(value = "/nganh-hoc", method = RequestMethod.GET)
	public String listNganhHoc(ModelMap map) {
		map.addAttribute("nganhHoc", nganhHocService.findAll());
		return "nganhHoc";
	}
}
