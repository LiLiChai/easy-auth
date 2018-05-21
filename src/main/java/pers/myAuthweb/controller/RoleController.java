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
import pers.myAuthweb.model.Role;
import pers.myAuthweb.service.RoleService;
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
			return ResultEntity.ok("ADD ROLE SUCCESSFULLY!!");
		} else {
			return ResultEntity.error("ADD ROLE UNSUCCESSFULLY!!！");
		}
	}
	
	@RequiresPermissions("system/role")
	@DeleteMapping("/{id}")
	public ResultEntity deleteRole(@PathVariable("id") String roleId) {
		if (roleService.deleteRole(roleId)) {
			return ResultEntity.ok("DELETE ROLE SUCCESSFULLY!!");
		} else {
			return ResultEntity.error("DELETE ROLE UNSUCCESSFULLY!!！");
		}
	}
	
	@RequiresPermissions("system/role")
	@PutMapping()
	public ResultEntity updateRole(Role role) {
		if (roleService.updateRole(role))
			return ResultEntity.ok("UPDATE ROLE SUCCESSFULLY!!!");
		else
			return ResultEntity.error("UPDATE ROLE UNSUCCESSFULLY!!!");
	}
	
	@RequiresPermissions("system/role")
	@PutMapping("/status")
	public ResultEntity updateStatus(String roleId, int status) {
		if (roleService.updateStatus(roleId, status))
			return ResultEntity.ok("UPDATE ROLE STATUS SUCCESSFULLY!!!");
		else
			return ResultEntity.error("UPDATE ROLE STATUS UNSUCCESSFULLY!!!");
	}
	
	public void updatePermission() {
		
	}
}
