package com.a2m.demoSpringSecurity.controller;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.a2m.demoSpringSecurity.dao.App_RoleDAO;
import com.a2m.demoSpringSecurity.dao.App_UserDAO;
import com.a2m.demoSpringSecurity.entity.app_user;

@Controller
public class homeController {
	
	@Autowired
	App_UserDAO userdao;
	
	@GetMapping("/home")
	public ModelAndView showHomePage() {
		ModelAndView mav = new ModelAndView("home");
		List<app_user> users = userdao.selectUser();
		for(app_user user: users) {
			System.out.println("ID: "+user.getId());
		}
		return mav;
	}
	
	@GetMapping("/userInfo")
	public ModelAndView showUserPage(Principal principal) {
		ModelAndView mav = new ModelAndView("UserPage");
		//lay role cua principle
		if(((Authentication) principal).getAuthorities().toString().contains("ROLE_ADMIN")==true) {
			List<app_user> users = userdao.selectUser();
			mav.addObject("users", users);
		}
		else {
			List<app_user> users = userdao.selectUserById(Integer.parseInt(principal.getName()));
			mav.addObject("users", users);
		}
		System.out.println(mav.toString());
		return mav;
	}
}
