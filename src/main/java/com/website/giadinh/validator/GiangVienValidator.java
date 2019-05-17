package com.website.giadinh.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.GiangVien;
import com.website.giadinh.service.GiangVienService;

@Component
public class GiangVienValidator implements Validator {
	@Autowired
	private GiangVienService giangVienService;

	@Override
	public boolean supports(Class<?> clazz) {
		return GiangVien.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		GiangVien giangVien = (GiangVien) target;

		if (!giangVien.getMode().equals("edit")) {
			if (giangVienService.isExistKey(giangVien.getMaGV())) {
				errors.rejectValue("maGV", "Unique.giangVien.maGV");
			}
		}

		if (giangVien.getNgaySinh() != null) {
			LocalDate localDate1 = giangVien.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localDate2 = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year1 = localDate1.getYear();
			int year2 = localDate2.getYear();

			if (year2 - year1 < 18) {
				errors.rejectValue("ngaySinh", "Select.validAge.giangVien.ngaySinh");
			}

			if (giangVien.getNgaySinh().after(today)) {
				errors.rejectValue("ngaySinh", "Select.validDate.giangVien.ngaySinh");
			}
		}

		if (giangVien.getNoiSinh().equals("NONE")) {
			errors.rejectValue("noiSinh", "Select.giangVien.noiSinh");
		}

		if (giangVien.getTrinhDo().equals("NONE")) {
			errors.rejectValue("trinhDo", "Select.giangVien.trinhDo");
		}

		if (giangVien.getKhoa().getMaKhoa().equals("NONE")) {
			errors.rejectValue("khoa", "Select.giangVien.khoa");
		}
	}
}