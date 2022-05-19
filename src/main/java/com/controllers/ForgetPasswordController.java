package com.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.User;
import com.services.Implementations.ForgetPasswordService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
@RestController
@RequestMapping("/forgetPassword")
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin("http://localhost:4200/")

public class ForgetPasswordController {
	@Autowired
	ForgetPasswordService forgetPasswordService;

	@PostMapping("/forgot_password")
	public String updateResetPasswordToken(@RequestBody User user) {
		return forgetPasswordService.updateResetPasswordToken(user.getEmail());
	}

	@PutMapping("/reset_password")
	public String getByResetPasswordToken(@Param(value = "token") String token, @RequestBody User user) {
		User userRetreived = forgetPasswordService.retrieveByResetPasswordToken(token);	
		return forgetPasswordService.updatePassword(userRetreived, user.getPassword());
	}
	
	@PutMapping("/updatePassword")
	public String updatePassword(@RequestBody User user) {
		return forgetPasswordService.updatePassword(user, user.getPassword());
	}
}

