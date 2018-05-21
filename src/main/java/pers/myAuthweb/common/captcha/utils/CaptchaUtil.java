package pers.myAuthweb.common.captcha.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class CaptchaUtil {

	//check vercode 
	public static boolean isVerified(String verKey, String verCode,
			HttpServletRequest request) {
		ServletContext servletContext = request.getSession()
				.getServletContext();
		String cacheVerCode = (String) servletContext.getAttribute("code_"
				+ verKey);
		return verCode.equals(cacheVerCode);
	}
}
