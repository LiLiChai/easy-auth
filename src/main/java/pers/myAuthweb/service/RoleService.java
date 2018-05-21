package pers.myAuthweb.service;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.model.Role;
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
