package pers.lihuan.authweb.controller;

import java.io.UnsupportedEncodingException;

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
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.exception.ParameterException;
import pers.lihuan.authweb.model.Role;
import pers.lihuan.authweb.service.RoleService;
/*
 * author : LH 2018-05-21 AM
 */
@RestController
@RequestMapping("api/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping()
	public PageResult<Role> getAllRoles(Integer page, Integer limit, String searchKey, String searchValue, Integer isDelete) throws UnsupportedEncodingException {
		if(searchValue != null){
			searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(page == null) {
			page = 0;
			limit = 0;
		}
		return roleService.getRoles(page, limit, searchKey, searchValue, isDelete);
	}
	
	@RequiresPermissions("system/role")
	@PostMapping()
	public ResultEntity addRole(Role role) {
		if (roleService.addRole(role)) {
			return ResultEntity.ok("添加角色成功！!!");
		} else {
			return ResultEntity.error("添加角色失败!!！");
		}
	}
	
	@RequiresPermissions("system/role")
	@DeleteMapping("/{id}")
	public ResultEntity deleteRole(@PathVariable("id") String roleId) throws BusinessException {
		if (roleService.deleteRole(roleId)) {
			return ResultEntity.ok("删除角色成功!!");
		} else {
			return ResultEntity.error("删除角色失败!!！");
		}
	}
	
	@RequiresPermissions("system/role")
	@PutMapping()
	public ResultEntity updateRole(Role role) {
		if (roleService.updateRole(role))
			return ResultEntity.ok("更新角色成功!!!");
		else
			return ResultEntity.error("更新角色失败!!!");
	}
	
	@RequiresPermissions("system/role")
	@PutMapping("/status")
	public ResultEntity updateStatus(String roleId, int status) throws ParameterException {
		if (roleService.updateStatus(roleId, status))
			return ResultEntity.ok("更新角色状态成功!!!");
		else
			return ResultEntity.error("更新角色状态失败!!!");
	}
}
