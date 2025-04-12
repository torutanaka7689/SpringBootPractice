package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

//お問い合わせ情報更新リクエストデータ

@Data
@EqualsAndHashCode(callSuper=false)
public class ContactFormUpdate extends ContactForm implements Serializable {
	
	//ユーザーID
	
	@NotNull
	private Long id;
}
