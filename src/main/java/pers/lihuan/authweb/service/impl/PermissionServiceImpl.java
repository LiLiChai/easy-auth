package pers.lihuan.authweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.dao.PermissionMapper;
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.model.Permission;
import pers.lihuan.authweb.model.PermissionExample;
import pers.lihuan.authweb.service.PermissionService;
import pers.lihuan.authweb.utils.StringUtil;
import pers.lihuan.authweb.utils.UUIDUtil;

/**
 * @author Fancy
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
				List<Permission> submenu = new ArrayList<Permission>();
				for(Permission one2 : permissions) {
					if(one1.getPermissionId().equals(one2.getParentId())) {
						submenu.add(one2);
					}
				}
				one1.setSubMenus(submenu);
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
		PageResult<Permission> results = new PageResult<Permission>();
		Page<Object> startPage = PageHelper.startPage(page, limit);
		List<Permission> permissions = permissionMapper.selectPermissions(searchKey, searchValue);
		results.setData(permissions);
		results.setCount(startPage.getTotal());
		return results;
	}

	@Override
	public boolean addPermission(Permission permission) {
		permission.setPermissionId(UUIDUtil.randomUUID8());
		permission.setCreateTime(new Date());
		if (StringUtil.isBlank(permission.getParentId())) {
			permission.setParentId("0");
		}
		return permissionMapper.insertSelective(permission) > 0;
	}

	@Override
	public boolean deletePermission(String permissionId) throws BusinessException {
		try{
			return permissionMapper.deleteByPrimaryKey(permissionId)>0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("权限已被使用");
		}
	}

	@Override
	public boolean updatePermission(Permission permission) {
		if (StringUtil.isBlank(permission.getParentId()))
			permission.setParentId("0");
		return permissionMapper.updateByPrimaryKeySelective(permission) > 0;
	}

	@Override
	public boolean updateStatus(String permissionId, int isDelete) {
		Permission permission = new Permission();
		permission.setPermissionId(permissionId);
		permission.setIsDelete(isDelete);
		return permissionMapper.updateByPrimaryKeySelective(permission) > 0;
	}

	@Override
	public boolean updateRolePermission(String roleId, String permissionIds) {
		return false;
	}
	
	@Override
	public List<Permission> getParentPermissions(int type) {
		List<Permission> result;
		if(type==0){
			PermissionExample example = new PermissionExample();
			example.setOrderByClause("order_number asc");
			PermissionExample.Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo("0");
			criteria.andPermissionTypeEqualTo(0);
			result = permissionMapper.selectByExample(example);
		}else{
			result = permissionMapper.selectButtonParent();
		}
		return result;
	}
	
}
