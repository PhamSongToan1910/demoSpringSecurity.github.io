package com.a2m.demoSpringSecurity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.a2m.demoSpringSecurity.entity.app_user;


@Mapper
public interface App_UserDAO {
	
	List<app_user>  selectUser();
	
	List<app_user>  selectUserById(int id);
}
