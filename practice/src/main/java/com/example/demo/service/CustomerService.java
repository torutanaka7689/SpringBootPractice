package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	//お問い合わせ情報 repository
	@Autowired
	private CustomerRepository customerRepository;
	
	//お問い合わせ情報 全検索
	public List<Customer> searchAll(){
		return customerRepository.findAll();
	}

}