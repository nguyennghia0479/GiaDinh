package com.website.giadinh.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.website.giadinh.entity.GiangVien;
import com.website.giadinh.entity.SinhVien;
import com.website.giadinh.entity.TaiKhoan;
import com.website.giadinh.service.GiangVienService;
import com.website.giadinh.service.SinhVienService;
import com.website.giadinh.service.TaiKhoanService;

@Controller
public class LoginController {
	@Autowired
	private TaiKhoanService taiKhoanService;

	@Autowired
	private GiangVienService giangVienService;
	
	@Autowired
	private SinhVienService sinhVienService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap map) {
		map.addAttribute("taiKhoan", new TaiKhoan());
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("maTK");
		session.removeAttribute("sv");
		session.removeAttribute("gv");
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcessing(@ModelAttribute("taiKhoan") @Valid TaiKhoan taiKhoan, BindingResult result,
			@RequestParam String maTK, @RequestParam String matKhau, HttpSession session, ModelMap map) {
		if (result.hasErrors()) {
			return "login";
		}
		
		taiKhoan = taiKhoanService.findById(maTK);
		if (taiKhoan == null) {
			map.addAttribute("errorUsername", true);
			map.addAttribute("msgUsername", "Không tìm thấy tên tài khoản");
			return "login";
		} else {
			if (!taiKhoan.getMatKhau().equals(matKhau)) {
				map.addAttribute("errorPassword", true);
				map.addAttribute("msgPassword", "Mật khẩu không đúng");
				return "login";
			}
		}

		if (taiKhoan.getQuyen() == 1) {
			session.setAttribute("maTK", maTK);
			return "redirect:/admin/khoa";
		} else if (taiKhoan.getQuyen() == 2) {
			GiangVien gv = giangVienService.findById(maTK);
			session.setAttribute("maTK", maTK);
			session.setAttribute("gv", gv);
			return "redirect:/user-giang-vien";
		} else {
			SinhVien sv = sinhVienService.findById(maTK);
			session.setAttribute("maTK", maTK);
			session.setAttribute("sv", sv);
			return "redirect:/user-sinh-vien";
		}
	}
	
	@RequestMapping(value = "/user-giang-vien", method = RequestMethod.GET)
	public String getGiangVien(ModelMap map) {
		return "userGiangVien";
	}
	
	@RequestMapping(value = "/user-sinh-vien", method = RequestMethod.GET)
	public String getSinhVien(ModelMap map) {
		return "userSinhVien";
	}
}