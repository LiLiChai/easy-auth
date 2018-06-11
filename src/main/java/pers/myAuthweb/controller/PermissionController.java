package pers.myAuthweb.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.common.ResultEntity;
import pers.myAuthweb.common.authz.annotation.RequiresPermissions;
import pers.myAuthweb.common.authz.annotation.RequiresRoles;
import pers.myAuthweb.exception.BusinessException;
import pers.myAuthweb.model.Permission;
import pers.myAuthweb.service.PermissionService;

/*
 * author : LH 2018-05-21 PM
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
//	@Autowired
//	AuthService authService;
	
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
	
	@RequiresRoles("admin")
	@PostMapping()
	public ResultEntity addPermissions(Permission permission) {
		if (permissionService.addPermission(permission))
			return ResultEntity.ok("ADD PERMISSION SUCCESSFULLY!!!");
		else
			return ResultEntity.error("ADD PERMISSION UNSUCCESSFULLY!!!");
	}

	@RequiresPermissions("role/permission")
	@DeleteMapping("/{permissionId}")
	public ResultEntity deletePermissions(@PathVariable("permissionId") String permissionId) throws BusinessException{
		if (permissionService.deletePermission(permissionId))
			return ResultEntity.ok("DELETE PERMISSION SUCCESSFULLY!!!");
		else
			return ResultEntity.error("DELETE PERMISSION UNSUCCESSFULLY!!!");
	}
	
	
	@RequiresRoles("admin")
	@PutMapping("status")
	public ResultEntity updateStatus(String permissionId, int status) {
		if (permissionService.updateStatus(permissionId, status))
			return ResultEntity.ok("UPDATE PERMISSION STATUS SUCCESSFULLY!!!");
		else
			return ResultEntity.error("UPDATE PERMISSION STATUS UNSUCCESSFULLY!!!");
	}
	
	public ResultEntity updatePermission(Permission permission) {
		if (permissionService.updatePermission(permission))
			return ResultEntity.ok("UPDATE PERMISSION IS SUCCESSFULLY!!!");
		else
			return ResultEntity.error("UPDATE PERMISSION IS UNSUCCESSFULLY!!!");
	}
	
	@RequiresRoles("admin")
	@PutMapping("/tree")
	public ResultEntity updateRolePermission(String roleId, String permissionIds) {
		if (permissionService.updateRolePermission(roleId, permissionIds))
			return ResultEntity.ok("UPDATE ROLE PERMISSION SUCCESSFULLY!!!");
		else
			return ResultEntity.error("UPDATE ROLE PERMISSION UNSUCCESSFULLY!!!");
	}
	
}
