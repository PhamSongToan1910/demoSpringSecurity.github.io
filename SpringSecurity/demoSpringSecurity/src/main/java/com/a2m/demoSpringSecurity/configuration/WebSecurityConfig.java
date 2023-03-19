package com.a2m.demoSpringSecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.a2m.demoSpringSecurity.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserDetailsServiceImpl userdetailsserviceimpl;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//cho phep truy cap trang / va trang /home ma khong can dang nhap
		http
			.authorizeRequests().antMatchers("/","/home").permitAll();
		
		//dang nhap vao trang userInfo voi quyen user va admin
		http	
			.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		//dang nhao vao trang admin voi quyen admin
		http
			.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		
		//ngoai le 403 duoc nem ra khi truy cap vao trang k co quyen
		http
			.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		//hien thi trang login
		http
			.formLogin().permitAll()
			.defaultSuccessUrl("/userInfo")
			.failureUrl("/login?success=fail");
		
		//log out
		http
			.logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
		
		return http.build();
	}

	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailsserviceimpl).passwordEncoder(passwordEncoder());

	}

	
}
