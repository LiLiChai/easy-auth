package pers.myAuthweb.dao;

import java.util.List;

import pers.myAuthweb.model.Permission;

/*
 * author : LH 2018-05-20 AM
 */
public interface PermissionMapper {

	List<Permission> selectPermissionByRoleId(String roleId);
	List<Permission> selectPermissionByUserId(String userId);

}