package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

//お問い合わせ情報 Entity

@Entity
@Data
@Table(name = "contacts")
public class Customer implements Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "building_name")
	private String buildingName;
	
	@Column(name = "contact_type")
	private String contactType;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "created_at")
	private Date createdAt;

}
