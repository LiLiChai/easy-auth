package pers.myAuthweb.service;

import java.util.List;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.exception.BusinessException;
import pers.myAuthweb.model.Permission;

/*
 * author : LH 2018-05-20 PM
 */
public interface PermissionService {
	
	List<Permission> getMenusByUser(String userId);
	
	List<Permission> getPermissionsByRole(String role);
	
	PageResult<Permission> getPermissions(Integer page, Integer limit, String searchKey, String searchValue);

	boolean addPermission(Permission permission);
	
	boolean deletePermission(String permissionId) throws BusinessException;
	
	boolean updatePermission(Permission permission);
	
	boolean updateStatus(String permissionId, int status);
	
	boolean updateRolePermission(String roleId, String permissionIds);
}
