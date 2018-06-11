package pers.myAuthweb.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.myAuthweb.common.CaptchaUtil;
import pers.myAuthweb.common.EncryptUtils;
import pers.myAuthweb.common.ResultEntity;
import pers.myAuthweb.common.authz.AuthUtil;
import pers.myAuthweb.model.LoginRecord;
import pers.myAuthweb.model.User;
import pers.myAuthweb.service.LoginRecordService;
import pers.myAuthweb.service.PermissionService;
import pers.myAuthweb.service.UserService;
import pers.myAuthweb.utils.DateUtil;
import pers.myAuthweb.utils.StringUtil;
import pers.myAuthweb.utils.UserAgentGetter;

/*
 * author : lh 2018-05-18 AM
 */
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	LoginRecordService loginRecordService;
	@Autowired
	PermissionService permissionService;
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("login")
	public ResultEntity login(String userName, String password, String captchaValue, String captchaKey,
			HttpServletRequest request) {
		logger.debug("account=" + userName);
		if (!StringUtil.isBlank(userName, password, captchaValue, captchaKey))
			return ResultEntity.error("input is exist empty!!! please check!!");
		if (!CaptchaUtil.checkCaptcha(captchaKey, captchaValue, request))
			return ResultEntity.error("Captcha is error!!!");

		User user = userService.getUserByUserName(userName);
		if (user == null)
			return ResultEntity.error("user is not exist!!!");

		if (user.getUserStatus() != 0)
			return ResultEntity.error("The user is locked!!!");

		String encryptPsw = EncryptUtils.encrytMd5(password, user.getUserId(), 3);
		logger.debug(encryptPsw);
		if (!user.getUserPassword().equals(encryptPsw))
			return ResultEntity.error("user or password is incorrect!!");

		addLoginRecord(request, user.getUserId());

		String token = AuthUtil.getInstance().createToken(user.getUserId(), DateUtil.getAppointDate(new Date(), 30));
		user.setUserPassword(null);
		return ResultEntity.ok("login successfullly!").put("user", user).put("token", token);
	}

	@DeleteMapping("login")
	public ResultEntity loginOut(HttpServletRequest request) {
		AuthUtil.getInstance().expireToken(getUserId(request), getToken(request));
		return ResultEntity.ok("login out successfully！");
	}

	@GetMapping("/menu")
	public ResultEntity navMenue(HttpServletRequest request) {
		return ResultEntity.ok().put("menus", permissionService.getMenusByUser(getUserId(request)));
	}

	public void addLoginRecord(HttpServletRequest request, String userId) {
		UserAgentGetter agentGetter = new UserAgentGetter(request);
		LoginRecord loginRecord = new LoginRecord();
		loginRecord.setUserId(userId);
		loginRecord.setIpAddress(agentGetter.getIpAddr());
		loginRecord.setDevice(agentGetter.getDevice());
		loginRecord.setBrowserType(agentGetter.getBrowser());
		loginRecord.setOsName(agentGetter.getOS());
		loginRecordService.addLoginRecord(loginRecord);
	}
}
