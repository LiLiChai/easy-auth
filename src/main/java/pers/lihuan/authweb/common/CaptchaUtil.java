package pers.lihuan.authweb.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/**
 * @author Fancy
 */
public class CaptchaUtil {
	
	public static boolean checkCaptcha(String captchaKey, String captchaCode, HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		String cacheVerCode = (String) servletContext.getAttribute("code_" + captchaKey);
		return captchaCode.equals(cacheVerCode);
	}
}
