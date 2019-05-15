package com.website.giadinh.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.NganhHoc;

@Component
public class NganhHocValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return NganhHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NganhHoc nganhHoc = (NganhHoc) target;
		if(nganhHoc.getKhoa().getMaKhoa().equals("NONE")) {
			errors.rejectValue("khoa", "Valid.nganhHoc.khoa");
		}
	}
}