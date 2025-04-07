package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;

@Controller
public class SignupController {
	
	@Autowired
	private SignupService signupService;
	
	@GetMapping("/admin/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		
		return "signup";
	}
	
	@PostMapping("/admin/signup")
	public String signup(@Validated @ModelAttribute("signupForm") SignupForm signupForm, BindingResult errorResult, HttpServletRequest request) {
		
		if (errorResult.hasErrors()) {
			return "signup";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("signupForm",signupForm);
		
		return "redirect:/admin/signup/confirm";
	}
	
	@GetMapping("/admin/signup/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		model.addAttribute("signupForm",signupForm);
		
		return "signup_confirmation";
	}
	
	@PostMapping("/admin/signup/register")
	public String register(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		
		signupService.saveAdmin(signupForm);
		
		return "redirect:/admin/signup/complete";
				
	}
	
	@GetMapping("/admin/signup/complete")
	public String complete(Model model,HttpServletRequest request) {
		
		if (request.getSession(false) == null) {
			return "redirect:/admin/signup";
		}
		
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		model.addAttribute("signupForm",signupForm);
		
		session.invalidate();
		
		return "SignupCompletion";
		
		
	}	

}
