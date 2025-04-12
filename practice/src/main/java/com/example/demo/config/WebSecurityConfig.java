package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests((requests) -> requests
				
				.requestMatchers("/","/contact/**","/complete","/admin/**","/contacts","/login?error","/h2-console/**").permitAll()
				.anyRequest().authenticated()
				)
				.formLogin((login) -> login
						.usernameParameter("username")
						.passwordParameter("password")
						// ログインを実行するページ
						.loginProcessingUrl("/admin/signin")
						// ログイン画面
						.loginPage("/admin/signin")
						// ログイン失敗時
						.failureUrl("/admin/signin?error")
						// ログイン成功後の画面
						.defaultSuccessUrl("/admin/contacts",true)
						// アクセス権
						.permitAll()
				)
				.logout((logout) -> logout
						//ログアウト処理
						.logoutUrl("/logout")
						.logoutSuccessUrl("/admin/signin")
						.permitAll()
						);
				
		
		return http.build();
	
	}
	//パスワードのハッシュ化
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
