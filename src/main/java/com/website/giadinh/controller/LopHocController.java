package com.website.giadinh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.service.LopHocService;
import com.website.giadinh.service.NganhHocService;

//@Controller
@RequestMapping(value = "/admin")
public class LopHocController extends PagedListHolderCustom<LopHoc> {
	@Autowired
	private LopHocService lopHocService;

	@Autowired
	private NganhHocService nganhHocService;

	@Override
	public void pagedListHolder(HttpServletRequest request, List<LopHoc> list, Integer p) {
		PagedListHolder<LopHoc> pagedListHolder = new PagedListHolder<LopHoc>(list);
		pagedListHolder.setMaxLinkedPages(5);
		request.getSession().setAttribute("pagedListHolder", pagedListHolder);
		if (p == null) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(p);
		}
	}

	@RequestMapping(value = "/lop-hoc", method = RequestMethod.GET)
	public String listLopHoc(HttpServletRequest request, @RequestParam(required = false) Integer p, ModelMap map) {
		List<LopHoc> list = lopHocService.findAll();
		pagedListHolder(request, list, p);
		map.addAttribute("pageURL", "lop-hoc");
		map.addAttribute("result", lopHocService.countList());
		return "lopHoc";
	}
}