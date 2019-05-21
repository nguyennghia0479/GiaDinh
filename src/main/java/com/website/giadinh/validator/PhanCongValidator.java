package com.website.giadinh.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.website.giadinh.entity.PhanCong;

@Component
public class PhanCongValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return PhanCong.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PhanCong phanCong = (PhanCong) target;
		if (phanCong.getLopHoc().getMaLop().equals("NONE")) {
			errors.rejectValue("lopHoc", "Select.phanCong.lopHoc");
		}

		if (phanCong.getMonHoc().getMaMH().equals("NONE")) {
			errors.rejectValue("monHoc", "Select.phanCong.monHoc");
		}

		if (phanCong.getGiangVien().getMaGV().equals("NONE")) {
			errors.rejectValue("giangVien", "Select.phanCong.giangVien");
		}

		if (phanCong.getNgayBD() != null && phanCong.getNgayKT() != null) {
			if (phanCong.getNgayBD().after(phanCong.getNgayKT())) {
				errors.rejectValue("ngayBD", "Select.ValidDate.phanCong.ngayBD");
			}

			if (phanCong.getNgayKT().before(phanCong.getNgayBD())) {
				errors.rejectValue("ngayKT", "Select.ValidDate.phanCong.ngayKT");
			}
		}
	}

}
