package pers.lihuan.authweb.common.authz;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pers.lihuan.authweb.common.authz.annotation.Logical;
import pers.lihuan.authweb.common.authz.annotation.RequiresPermissions;
import pers.lihuan.authweb.common.authz.annotation.RequiresRoles;
import pers.lihuan.authweb.common.authz.exception.UnauthorizedException;




/**
 * 处理基于token请求的拦截器
 */
public class ApiInterceptor implements HandlerInterceptor {

	public void setUserRealm(IUserRealm userRealm) {
		AuthUtil.getInstance().setUserRealm(userRealm);
	}

	public void setTokenKey(String tokenKey) {
		AuthUtil.getInstance().setTokenKey(tokenKey);
	}

	public void setCache(IEtpCache cache) {
		AuthUtil.getInstance().setCache(cache);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AuthUtil subjectUtil = AuthUtil.getInstance();
		
		String token = subjectUtil.getRequestToken(request);
		String userId = subjectUtil.parseToken(token).getSubject();
		
		// 检查权限
		if (handler instanceof HandlerMethod) {
			Method method = ((HandlerMethod) handler).getMethod();
			if (method != null) {
				if (!checkPermission(method, userId) || !checkRole(method, userId)) {
					throw new UnauthorizedException();
				}
			}
		}
		request.setAttribute("userId", userId);
		return true;
	}

	/**
	 * 检查权限
	 * 
	 * @param method
	 * @param userId
	 * @return
	 */
	private boolean checkPermission(Method method, String userId) {
		RequiresPermissions annotation = method
				.getAnnotation(RequiresPermissions.class);
		if (annotation == null) {
			return true;
		}
		String[] requiresPermissions = annotation.value();
		Logical logical = annotation.logical();
		return AuthUtil.getInstance().hasPermission(userId,
				requiresPermissions, logical);
	}

	/**
	 * 检查角色
	 * 
	 * @param method
	 * @param userId
	 * @return
	 */
	private boolean checkRole(Method method, String userId) {
		RequiresRoles annotation = method.getAnnotation(RequiresRoles.class);
		if (annotation == null) {
			return true;
		}
		String[] requiresRoles = annotation.value();
		Logical logical = annotation.logical();
		return AuthUtil.getInstance()
				.hasRole(userId, requiresRoles, logical);
	}
}