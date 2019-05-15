package com.website.giadinh.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
	public void initialize(Name paramA) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (name == null) {
			return false;
		}
		// return name.matches("[^\\d!@#$%^&*(){}/\\//+?.,]{0,50}");
		return name.matches(
				"[a-z-A-Z_áàảãạâấầẩẫậăắằẳẵặéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđ ÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ]{0,50}");
	}
}