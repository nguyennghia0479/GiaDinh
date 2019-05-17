package com.website.giadinh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.Khoa;
import com.website.giadinh.service.KhoaService;

@Component
public class KhoaValidator implements Validator {
	@Autowired
	private KhoaService khoaService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Khoa.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Khoa khoa = (Khoa) target;
		if(!khoa.getMode().equals("edit")) {
			if(khoaService.isExistKey(khoa.getMaKhoa())) {
				errors.rejectValue("maKhoa", "Unique.khoa.maKhoa");
			}
		}	
	}
}