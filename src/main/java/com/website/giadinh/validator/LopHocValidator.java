package com.website.giadinh.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.service.LopHocService;

@Component
public class LopHocValidator implements Validator {
	@Autowired
	private LopHocService lopHocService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LopHoc.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LopHoc lopHoc = (LopHoc) target;
		
		if(!lopHoc.getMode().equals("edit")) {
			if(lopHocService.isExistKey(lopHoc.getMaLop())) {
				errors.rejectValue("maLop", "Unique.lopHoc.maLop");
			}
		}
		
		if(lopHoc.getNganhHoc().getMaNganh().equals("NONE")) {
			errors.rejectValue("nganhHoc", "Select.lopHoc.nganhHoc");
		}
	}
}