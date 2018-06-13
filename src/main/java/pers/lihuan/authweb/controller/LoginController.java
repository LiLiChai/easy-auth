package pers.lihuan.authweb.controller;

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

import pers.lihuan.authweb.common.CaptchaUtil;
import pers.lihuan.authweb.common.EncryptUtils;
import pers.lihuan.authweb.common.ResultEntity;
import pers.lihuan.authweb.common.authz.AuthUtil;
import pers.lihuan.authweb.model.LoginRecord;
import pers.lihuan.authweb.model.User;
import pers.lihuan.authweb.service.LoginRecordService;
import pers.lihuan.authweb.service.PermissionService;
import pers.lihuan.authweb.service.UserService;
import pers.lihuan.authweb.utils.DateUtil;
import pers.lihuan.authweb.utils.StringUtil;
import pers.lihuan.authweb.utils.UserAgentGetter;

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
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("login")
	public ResultEntity login(String userName, String password, String captchaValue, String captchaKey,
			HttpServletRequest request) {
		
		if (!StringUtil.isBlank(userName, password, captchaValue, captchaKey))
			return ResultEntity.error("输入存在空白！！！");
		if (!CaptchaUtil.checkCaptcha(captchaKey, captchaValue, request))
			return ResultEntity.error("验证码输入错误！！！");

		User user = userService.getUserByUserName(userName);
		if (user == null)
			return ResultEntity.error("用户名不存在!!!");

		if (user.getUserStatus() != 0)
			return ResultEntity.error("该用户已锁定!!!");

		String encryptPsw = EncryptUtils.encrytMd5(password, user.getUserId(), 3);
		if (!user.getUserPassword().equals(encryptPsw))
			return ResultEntity.error("用户名或密码错误！！！");

		addLoginRecord(request, user.getUserId());
		String token = AuthUtil.getInstance().createToken(user.getUserId(), DateUtil.getAppointDate(new Date(), 30));
		user.setUserPassword(null);
		logger.debug("======================登录成功！！！=========================");
		return ResultEntity.ok("登录成功！！!").put("user", user).put("token", token);
	}

	@DeleteMapping("login")
	public ResultEntity loginOut(HttpServletRequest request) {
		AuthUtil.getInstance().expireToken(getUserId(request), getToken(request));
		return ResultEntity.ok("退出登录成功！！！");
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
