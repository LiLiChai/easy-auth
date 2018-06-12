package pers.lihuan.authweb.controller;

import javax.servlet.http.HttpServletRequest;

/*
 * author : LH 2018-05-18 AM
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
