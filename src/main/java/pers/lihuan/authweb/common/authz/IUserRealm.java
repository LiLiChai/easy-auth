package pers.lihuan.authweb.common.authz;

import java.util.Set;

/**
 * User授权接口
 * @author Fancy
 */
public abstract class IUserRealm {

	/**
	 * 获取用户角色
	 */
	public abstract Set<String> getUserRoles(String userId);

	/**
	 * 获取用户权限
	 */
	public abstract Set<String> getUserPermissions(String userId);

	/**
	 * 是否是单账号登录
	 */
	public boolean isSingleUser() {
		return false;
	}
}
