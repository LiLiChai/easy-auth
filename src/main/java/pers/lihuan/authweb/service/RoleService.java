package pers.lihuan.authweb.service;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.exception.ParameterException;
import pers.lihuan.authweb.model.Role;

/**
 * @author Fancy
 */
public interface RoleService {
	
	PageResult<Role> getRoles(Integer page, Integer limit, String searchKey, String searchValue, Integer isDelete);

	boolean addRole(Role role);
	
	boolean deleteRole(String roleId) throws BusinessException;
	
	boolean updateRole(Role role);
	
	boolean updateStatus(String roleId, int status) throws ParameterException;
	
}
