package com.website.giadinh.validator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.SinhVien;
import com.website.giadinh.service.SinhVienService;

@Component
public class SinhVienValidator implements Validator {
	@Autowired
	private SinhVienService sinhVienService;

	@Override
	public boolean supports(Class<?> clazz) {
		return SinhVien.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		SinhVien sinhVien = (SinhVien) target;

		if (!sinhVien.getMode().equals("edit")) {
			if (sinhVienService.isExistKey(sinhVien.getMaSV())) {
				errors.rejectValue("maSV", "Unique.sinhVien.maSV");
			}
		}

		if (sinhVien.getNgaySinh() != null) {
			LocalDate localDate1 = sinhVien.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localDate2 = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int year1 = localDate1.getYear();
			int year2 = localDate2.getYear();

			if (year2 - year1 < 10) {
				errors.rejectValue("ngaySinh", "Select.validAge.sinhVien.ngaySinh");
			}

			if (sinhVien.getNgaySinh().after(today)) {
				errors.rejectValue("ngaySinh", "Select.validDate.sinhVien.ngaySinh");
			}
		}

		if (sinhVien.getNoiSinh().equals("NONE")) {
			errors.rejectValue("noiSinh", "Select.sinhVien.noiSinh");
		}

		if (sinhVien.getLopHoc().getMaLop().equals("NONE")) {
			errors.rejectValue("lopHoc", "Select.sinhVien.lopHoc");
		}
	}
}