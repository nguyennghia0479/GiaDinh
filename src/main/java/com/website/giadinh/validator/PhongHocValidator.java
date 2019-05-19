package com.website.giadinh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.PhongHoc;
import com.website.giadinh.service.PhongHocService;

@Component
public class PhongHocValidator implements Validator {
	@Autowired
	private PhongHocService phongHocService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PhongHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PhongHoc phongHoc = (PhongHoc) target;
		if (!phongHoc.getMode().equals("edit")) {
			if (phongHocService.isExistKey(phongHoc.getMaPH())) {
				errors.rejectValue("maPH", "Unique.phongHoc.maPH");
			}
		}
	}
}