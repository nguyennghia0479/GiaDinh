package com.website.giadinh.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.LopHoc;

@Component
public class LopHocValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return LopHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LopHoc lopHoc = (LopHoc) target;
		if(lopHoc.getNganhHoc().getMaNganh().equals("NONE")) {
			errors.rejectValue("nganhHoc", "Valid.lopHoc.nganhHoc");
		}
	}
}