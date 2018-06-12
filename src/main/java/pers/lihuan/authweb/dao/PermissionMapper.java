package pers.lihuan.authweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.lihuan.authweb.model.Permission;

/*
 * author : LH 2018-05-20 AM
 */
public interface PermissionMapper {

	List<Permission> selectPermissionByRoleId(String roleId);
	List<Permission> selectPermissionByUserId(String userId);
	
	List<Permission> selectPermissions(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue);

}