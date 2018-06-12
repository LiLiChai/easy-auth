package pers.lihuan.authweb.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

import pers.lihuan.authweb.common.authz.AuthUtil;
import pers.lihuan.authweb.common.authz.IUserRealm;
import pers.lihuan.authweb.model.Permission;
import pers.lihuan.authweb.model.User;
import pers.lihuan.authweb.service.PermissionService;
import pers.lihuan.authweb.service.UserService;

/*
 * author : LH 2018-05-18 PM
 */
public class UserRealm extends IUserRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public Set<String> getUserRoles(String userId) {
		Set<String> roles = new HashSet<String>();
		User user = userService.getUserById(userId);
		if(user != null){
			roles.add(user.getRoleId());
		}
		return roles;
	}

	@Override
	public Set<String> getUserPermissions(String userId) {
		Set<String> permissionValues = new HashSet<String>();
		List<String> userRoles = AuthUtil.getInstance().getUserRoles(userId);
		if(userRoles.size()>0){
			List<Permission> permissions = permissionService.getPermissionsByRole(userRoles.get(0));
			for (int i = 0; i < permissions.size(); i++) {
				String permissionValue = permissions.get(i).getPermissionValue();
				if(permissionValue!=null && !permissionValue.isEmpty()){
					permissionValues.add(permissionValue);
				}
			}
		}
		return permissionValues;
	}
}