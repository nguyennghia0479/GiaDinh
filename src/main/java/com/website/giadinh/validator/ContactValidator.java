package com.website.giadinh.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactValidator implements ConstraintValidator<Contact, String> {
	public void initialize(Contact paramA) {
	}
	
	@Override
	public boolean isValid(String contact, ConstraintValidatorContext context) {
		if(contact == null) {
			return false;
		}
		return contact.matches("\\d{0,10}");
	}

}
