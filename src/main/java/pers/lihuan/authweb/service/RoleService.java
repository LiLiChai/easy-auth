package pers.lihuan.authweb.service;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.model.Role;
/*
 * author : LH 2018-05-20 PM
 */
public interface RoleService {
	
	PageResult<Role> getRoles(Integer page, Integer limit, String searchKey, String searchValue, Integer isDelete);

	boolean addRole(Role role);
	
	boolean deleteRole(String roleId);
	
	boolean updateRole(Role role);
	
	boolean updateStatus(String roleId, int status);
	
}
