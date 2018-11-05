package pers.lihuan.authweb.dao;

import java.util.List;

import pers.lihuan.authweb.model.Role;
import pers.lihuan.authweb.model.RoleExample;

/**
 * @author Fancy
 */
public interface RoleMapper {
	
	List<Role> selectByExample(RoleExample example);
	
	int insertRole(Role role);
	
	int deleteByPrimaryKey(String roleId);
	
	int updateByPrimaryKeySelective(Role role);
	    
}