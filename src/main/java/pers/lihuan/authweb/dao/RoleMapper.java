package pers.lihuan.authweb.dao;

import java.util.List;

import pers.lihuan.authweb.model.Role;
import pers.lihuan.authweb.model.RoleExample;

/*
 * author : LH 2018-05-20 PM
 */
public interface RoleMapper {
	
	List<Role> selectByExample(RoleExample example);
	
	int insertRole(Role role);
	
}