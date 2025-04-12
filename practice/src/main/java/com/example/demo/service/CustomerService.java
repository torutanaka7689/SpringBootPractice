package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.form.ContactFormUpdate;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService {
	
	//お問い合わせ情報 repository
	@Autowired
	private CustomerRepository customerRepository;
	
	//お問い合わせ情報 全検索
	public List<Customer> searchAll(){
		return customerRepository.findAll();
	}
	
	//お問い合わせ詳細ID検索
	public Customer findById(Long id) {
		return customerRepository.findById(id).get();
	}
	
	//お問い合わせ詳細更新
	public void update(ContactFormUpdate contactFormUpdate) {
		Customer customer = findById(contactFormUpdate.getId());
		customer.setLastname(contactFormUpdate.getLastName());
		customer.setFirstname(contactFormUpdate.getFirstName());
		customer.setEmail(contactFormUpdate.getEmail());
		customer.setPhone(contactFormUpdate.getPhone());
		customer.setZipCode(contactFormUpdate.getZipCode());
		customer.setAddress(contactFormUpdate.getAddress());
		customer.setBuildingName(contactFormUpdate.getBuildingName());
		customer.setContactType(contactFormUpdate.getContactType());
		customer.setBody(contactFormUpdate.getBody());
		customer.setUpdatedAt(new Date());
		
		customerRepository.save(customer); 
		
	}
	
	//お問い合わせ削除
	public void delete(Long id) {
		Customer customer = findById(id);
		customerRepository.delete(customer);
		
	}

}