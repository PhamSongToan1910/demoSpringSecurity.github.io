package com.a2m.demoSpringSecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.a2m.demoSpringSecurity.dao.App_UserDAO;
import com.a2m.demoSpringSecurity.dao.User_RoleDAO;
import com.a2m.demoSpringSecurity.entity.app_user;

@org.springframework.stereotype.Service

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	App_UserDAO userdao;
	
	@Autowired
	User_RoleDAO userroledao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<app_user> users = userdao.selectUserById(Integer.parseInt(username));
		
		List<String> roles = userroledao.selectUserRoleByID(Integer.parseInt(username));
		
		 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		 for(String role: roles) {
			 GrantedAuthority authority = new SimpleGrantedAuthority(role);
			 grantList.add(authority);
		 }
		 
		 UserDetails user = (UserDetails) new User(String.valueOf(users.get(0).getId()), users.get(0).getEncryted_password(), grantList);
		 return user;
	}
}
