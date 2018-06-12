package pers.lihuan.authweb.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

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
import pers.lihuan.authweb.common.authz.AuthUtil;
import pers.lihuan.authweb.common.authz.annotation.RequiresRoles;
import pers.lihuan.authweb.common.encrypt.utils.EndecryptUtils;
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.exception.ParameterException;
import pers.lihuan.authweb.model.User;
import pers.lihuan.authweb.service.UserService;


/*
 * author : LH 2018-05-20 AM
 */
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
	
	@Autowired
	UserService userService;
	
	@GetMapping()
	public PageResult<User> getUsers(Integer page, Integer limit, Integer status, String searchKey, String searchValue) throws UnsupportedEncodingException {
		if(searchValue != null){
			searchValue = new String(searchValue.getBytes("ISO-8859-1"), "UTF-8");
		}
		if(page == null) {
			page = 0;
			limit = 0;
		}
		return userService.getUsers(page, limit, status, searchKey, searchValue);
	}
	
	@RequiresRoles("admin")
	@PostMapping()
	public ResultEntity addUser(User user) throws BusinessException {
		user.setUserPassword("316412316");
		if (userService.addUser(user))
			return ResultEntity.ok("ADD USER SUCCESSFULLY!!!");
		else
			return ResultEntity.error("ERROR! PLEASE TRY AGAIN!!!");
	}
	
	@RequiresRoles("admin")
	@DeleteMapping("/{userId}")
	public ResultEntity deleteUser(@PathVariable("userId") String userId) throws BusinessException {
		if(userService.deleteUser(userId)){
			AuthUtil.getInstance().expireToken(userId);
			return ResultEntity.ok("DELETE SUCCESSFULLY!!!");
		}else{
			return ResultEntity.error("DELETE UNSUCCESSFULLY!!!");
		}
	}
	
	@RequiresRoles("admin")
	@PutMapping()
	public ResultEntity updateUser(User user) {
		if(userService.updateUser(user)){
			AuthUtil.getInstance().updateCacheRoles(user.getUserId());
			return ResultEntity.ok("修改成功");
		}else{
			return ResultEntity.error("修改失败");
		}
	}
	
	
	@RequiresRoles("admin")
	@PutMapping("status")
	public ResultEntity updateStatus(String userId, int status) throws ParameterException {
		if(userService.updateUserStatus(userId, status)){
			AuthUtil.getInstance().expireToken(userId);
			return ResultEntity.ok();
		}else{
			return ResultEntity.error();
		}
	}
	
	/**
	 * 修改自己密码
	 */
	@PutMapping("psw")
	public ResultEntity updatePassword(String oldPsw, String newPsw, HttpServletRequest request) {
		if(true){
			return ResultEntity.error("演示系统关闭该功能");
		}
		@SuppressWarnings("unused")
		String userId = getUserId(request);
		String encryPsw = EndecryptUtils.encrytMd5(oldPsw, userId, 3);
		User tempUser = userService.getUserById(userId);
		if(tempUser==null||!encryPsw.equals(tempUser.getUserPassword())){
			return ResultEntity.error("旧密码输入不正确");
		}
		if(userService.updateUserPassword(userId, newPsw)) {
			AuthUtil.getInstance().expireToken(userId);
			return ResultEntity.ok();
		}else{
			return ResultEntity.error();
		}
	}
	
	@RequiresRoles("admin")
	@PutMapping("psw/{userId}")
	public ResultEntity resetPassword(@PathVariable("userId") String userId, HttpServletRequest request) {
		if(userService.updateUserPassword(userId, "316412316")) {
			AuthUtil.getInstance().expireToken(userId);
			return ResultEntity.ok();
		}else{
			return ResultEntity.error();
		}
	}
	
}
