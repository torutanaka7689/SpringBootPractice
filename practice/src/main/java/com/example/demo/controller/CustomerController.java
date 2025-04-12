package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Customer;
import com.example.demo.form.ContactFormUpdate;
import com.example.demo.service.CustomerService;

//お問い合わせ情報コントローラ
@Controller
public class CustomerController {
	
	//お問い合わせ情報Service
	
	@Autowired
	private CustomerService customerService;
	
	//お問い合わせ一覧表示
	
	@GetMapping(value = "/admin/contacts")
	public String displayList(Model model) {
		List<Customer> customers = customerService.searchAll();
		model.addAttribute("customerlist",customers);

		return "contacts";
		
		
	}
	
	//お問い合わせ詳細画面表示
	
	@GetMapping(value = "/admin/contacts/{id}")
	public String displayView(@PathVariable Long id,Model model) {
		
		Customer customer = customerService.findById(id);
		model.addAttribute("customerData",customer);

		return "view";
	}
	
	//お問い合わせ編集画面を表示
	
	@GetMapping("/admin/contacts/{id}/edit")
	public String displayEdit(@PathVariable Long id, Model model) {
		Customer customer = customerService.findById(id);
		ContactFormUpdate contactFormUpdate = new ContactFormUpdate();
		contactFormUpdate.setId(customer.getId());
		contactFormUpdate.setLastName(customer.getLastname());
		contactFormUpdate.setFirstName(customer.getFirstname());
		contactFormUpdate.setEmail(customer.getEmail());
		contactFormUpdate.setPhone(customer.getPhone());
		contactFormUpdate.setZipCode(customer.getZipCode());
		contactFormUpdate.setAddress(customer.getAddress());
		contactFormUpdate.setBuildingName(customer.getBuildingName());
		contactFormUpdate.setContactType(customer.getContactType());
		contactFormUpdate.setBody(customer.getBody());
		
		model.addAttribute("contactFormUpdate",contactFormUpdate);
		
		return "edit";
	}
	
	//お問い合わせ変更確認
	
	@RequestMapping(value = "/admin/contacts/update",method = RequestMethod.POST)
	public String update(@Validated @ModelAttribute ContactFormUpdate contactFormUpdate,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError",errorList);
			
			return "edit";
			
		}
	
	//お問い合わせ詳細の更新
		customerService.update(contactFormUpdate);
		return String.format("redirect:/admin/contacts/%d", contactFormUpdate.getId());
	}
	
	
	//お問い合わせ情報の削除
	
	@GetMapping("/admin/contacts/{id}/delete")
	public String delete(@PathVariable Long id , Model model) {
		
		customerService.delete(id);
		return "redirect:/admin/contacts";
	}
	

}
