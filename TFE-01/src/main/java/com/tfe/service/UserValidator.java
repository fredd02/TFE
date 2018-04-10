package com.tfe.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tfe.model.User;

@Component
public class UserValidator implements Validator{

	

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User)o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if(user.getUsername().length() <5 || user.getUsername().length() >32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length() <5 || user.getPassword().length() >32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if(!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm","Diff.userForm.passwordConfirm");
		}
	}
	
public void validatePwd(String password, String passwordConfirm, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(password.length()<5 || password.length()>32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if(!passwordConfirm.equals(password)) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}

	

}
