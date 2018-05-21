package pers.myAuthweb.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/*
 * author : LH 2018-05-18 PM
 */
public class CaptchaUtil {
	
	public static boolean checkCaptcha(String captchaKey, String captchaCode, HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		String cacheVerCode = (String) servletContext.getAttribute("code_" + captchaKey);
		return captchaCode.equals(cacheVerCode);
	}
}
