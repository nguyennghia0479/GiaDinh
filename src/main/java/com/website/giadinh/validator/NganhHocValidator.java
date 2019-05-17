package com.website.giadinh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.NganhHoc;
import com.website.giadinh.service.NganhHocService;

@Component
public class NganhHocValidator implements Validator {
	@Autowired
	private NganhHocService nganhHocService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NganhHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NganhHoc nganhHoc = (NganhHoc) target;
		
		if(!nganhHoc.getMode().equals("edit")) {
			if(nganhHocService.isExistKey(nganhHoc.getMaNganh())) {
				errors.rejectValue("maNganh", "Unique.nganhHoc.maNganh");
			}
		}
			
		if(nganhHoc.getKhoa().getMaKhoa().equals("NONE")) {
			errors.rejectValue("khoa", "Select.nganhHoc.khoa");
		}
	}
}