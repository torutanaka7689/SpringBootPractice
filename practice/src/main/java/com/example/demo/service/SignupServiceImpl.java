package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.SignupRepository;

@Service
@Transactional
public class SignupServiceImpl implements SignupService {
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void saveAdmin(SignupForm signupForm) {
		
		Admin admin = new Admin();
		
		admin.setLastName(signupForm.getLastName());
		admin.setFirstName(signupForm.getFirstName());
		admin.setEmail(signupForm.getEmail());
		admin.setPass(passwordEncoder.encode(signupForm.getPass()));
		
		signupRepository.save(admin);

	}

}
