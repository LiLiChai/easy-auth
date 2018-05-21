package pers.myAuthweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.dao.PermissionMapper;
import pers.myAuthweb.model.Permission;
import pers.myAuthweb.service.PermissionService;

/*
 * author : LH 2018-05-20 PM
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	@Override
	public List<Permission> getMenusByUser(String userId) {
		List<Permission> results = new ArrayList<Permission>();
		List<Permission> permissions = permissionMapper.selectPermissionByUserId(userId);
		for(Permission one1 : permissions) {
			if("0".equals(one1.getParentId())){
				List<Permission> subMenu = new ArrayList<Permission>();
				for(Permission one2 : permissions) {
					if(one1.getPermissionId().equals(one2.getParentId())) {
						subMenu.add(one2);
					}
				}
				one1.setSubMenus(subMenu);
				results.add(one1);
			}
		}
		return results;
	}

	@Override
	public List<Permission> getPermissionsByRole(String roleId) {
		return permissionMapper.selectPermissionByRoleId(roleId);
	}

	@Override
	public PageResult<Permission> getPermissions(Integer page, Integer limit, String searchKey, String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePermission(String permissionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatus(String permissionId, int status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRolePermission(String roleId, String permissionIds) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
