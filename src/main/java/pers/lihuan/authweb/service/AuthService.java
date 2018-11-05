package pers.lihuan.authweb.service;

import java.util.List;

import pers.lihuan.authweb.model.MenuTree;

/**
 * @author Fancy
 */
public interface AuthService {
	/**
	 * 根据角色id查询角色的选中和未选中权限
	 */
	public List<MenuTree> getPermissionTree(String roleId);

	/**
	 * 修改角色的菜单权限
	 */
	public boolean updateRolePermission(String roleId, List<String> permissionIds);
}
