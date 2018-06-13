package pers.lihuan.authweb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.common.ResultEntity;
import pers.lihuan.authweb.common.authz.annotation.RequiresPermissions;
import pers.lihuan.authweb.common.authz.annotation.RequiresRoles;
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.model.MenuTree;
import pers.lihuan.authweb.model.Permission;
import pers.lihuan.authweb.service.AuthService;
import pers.lihuan.authweb.service.PermissionService;

/*
 * author : LH 2018-05-21 PM
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	@Autowired
	AuthService authService;
	
	/**
	 * 角色权限菜单树
	 */
	@GetMapping("/tree/{roleId}")
	public List<MenuTree> listPermTree(@PathVariable("roleId") String roleId){
		return authService.getPermissionTree(roleId);
	}
	
	/**
	 * 查询所有的父菜单
	 */
	@GetMapping("/parent/{type}")
	public List<Permission> listParent(@PathVariable("type") int type){
		return permissionService.getParentPermissions(type);
	}
	/*
	 * 查询权限
	 */
	@GetMapping()
	public PageResult<Permission> getPermissions(Integer page, Integer limit, String searchKey, String searchValue) throws UnsupportedEncodingException{
		if(searchValue != null){
			searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(page == null) {
			page = 0;
			limit = 0;
		}
		return permissionService.getPermissions(page, limit, searchKey, searchValue);
	}
	
	/*
	 * 添加权限
	 */
	@RequiresRoles("admin")
	@PostMapping()
	public ResultEntity addPermissions(Permission permission) {
		if (permissionService.addPermission(permission))
			return ResultEntity.ok("添加权限成功!!!");
		else
			return ResultEntity.error("添加权限失败!!!");
	}

	/*
	 * 删除权限
	 */
	@RequiresPermissions("system/permission")
	@DeleteMapping("/{permissionId}")
	public ResultEntity deletePermissions(@PathVariable("permissionId") String permissionId) throws BusinessException{
		if (permissionService.deletePermission(permissionId))
			return ResultEntity.ok("删除权限成功!!!");
		else
			return ResultEntity.error("删除权限失败!!!");
	}
	
	/*
	 * 更新权限
	 */
	@RequiresRoles("admin")
	@PutMapping()
	public ResultEntity updatePermission(Permission permission) {
		if (permissionService.updatePermission(permission))
			return ResultEntity.ok("权限更新成功!!!");
		else
			return ResultEntity.error("权限更新失败!!!");
	}
	/*
	 * 更新权限状态
	 */
	@RequiresRoles("admin")
	@PutMapping("status")
	public ResultEntity updateStatus(String permissionId, int status) {
		if (permissionService.updateStatus(permissionId, status))
			return ResultEntity.ok("权限状态更新成功!!!");
		else
			return ResultEntity.error("权限状态更新失败!!!");
	}
	
	
	/*
	 * 更新角色所拥有的权限
	 */
	@RequiresRoles("admin")
	@PutMapping("/tree")
	public ResultEntity updateRolePermission(String roleId, String permissionIds) {
		if (permissionService.updateRolePermission(roleId, permissionIds))
			return ResultEntity.ok("更新角色权限成功!!!");
		else
			return ResultEntity.error("更新角色权限失败!!!");
	}
	
	
	
}
