package com.example.demo.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;

@Component
@Transactional
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

@Autowired
private AppUserRepository appUserRepository;
	
	
	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException,ServletException{
		
		String email = authentication.getName();
		
		AppUser appUser = appUserRepository.findByEmail(email).get();
		
		appUser.setLastLogin(LocalDateTime.now());
		
		appUserRepository.save(appUser);
		
		response.sendRedirect("/admin/contacts");
	}


}
