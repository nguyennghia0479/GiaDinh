package com.website.giadinh.validator;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.GiangVien;

@Component
public class GiangVienValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return GiangVien.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gioiTinh", "Valid.gioiTinh");
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		
		GiangVien gv = (GiangVien) target;
		if(gv.getNgaySinh().after(today)) {
			errors.rejectValue("ngaySinh", "Valid.giangVien.ngaySinh");
		}
		
		if(gv.getNoiSinh().equals("NONE")) {
			errors.rejectValue("noiSinh", "Valid.giangVien.noiSinh");
		}
		
		if (gv.getTrinhDo().equals("NONE")) {
			errors.rejectValue("trinhDo", "Valid.giangVien.trinhDo");
		}
		
		if(gv.getKhoa().getMaKhoa().equals("NONE")) {
			errors.rejectValue("khoa", "Valid.giangVien.khoa");
		}
	}
}