package pers.lihuan.authweb.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Fancy
 */
public class BaseController {
	
	public String getUserId(HttpServletRequest request) {
		return (String) request.getAttribute("userId");
	}
	
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (token == null)
			token = request.getParameter("token");
		return token;
	}
}
