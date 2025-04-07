package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

//お問い合わせ情報コントローラ
@Controller
public class CustomerController {
	
	//お問い合わせ情報Service
	
	@Autowired
	private CustomerService customerService;
	
	//お問い合わせ一覧表示
	
	@GetMapping(value = "/contacts")
	public String displayList(Model model) {
		List<Customer> customers = customerService.searchAll();
		System.out.print(customers); // <-- 出力してみる
		model.addAttribute("customerlist",customers);
		return "contacts";
		
		
	}
	
	//お問い合わせ詳細画面表示
	
	@GetMapping(value = "/contacts/{id}")
	public String displayView(@PathVariable Long id,Model model) {
		return "view";
	}
	
	

}
