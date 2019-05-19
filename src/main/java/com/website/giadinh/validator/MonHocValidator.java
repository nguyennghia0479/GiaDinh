package com.website.giadinh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.MonHoc;
import com.website.giadinh.service.MonHocService;

@Component
public class MonHocValidator implements Validator {
	@Autowired
	private MonHocService monHocService;

	@Override
	public boolean supports(Class<?> clazz) {
		return MonHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MonHoc monHoc = (MonHoc) target;
		if (!monHoc.getMode().equals("edit")) {
			if (monHocService.isExistKey(monHoc.getMaMH())) {
				errors.rejectValue("maMH", "Unique.monHoc.maMH");
			}
		}

		if (monHoc.getSoTC() == -1) {
			errors.rejectValue("soTC", "Select.monHoc.soTC");
		}

		if (monHoc.getLyThuyet() == -1) {
			errors.rejectValue("lyThuyet", "Select.monHoc.lyThuyet");
		}

		if (monHoc.getThucHanh() == -1) {
			errors.rejectValue("thucHanh", "Select.monHoc.thucHanh");
		}
	}
}