package pers.myAuthweb.dao;

import java.util.List;

import pers.myAuthweb.model.Role;
import pers.myAuthweb.model.RoleExample;

/*
 * author : LH 2018-05-20 PM
 */
public interface RoleMapper {
	List<Role> selectByExample(RoleExample example);
	
}