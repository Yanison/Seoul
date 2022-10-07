package com.seoul.infra.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.seoul.infra.common.basic.Constants;

public class CheckLoginSessionInterception extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("idToken") != null) {
			
		} else {
//			response.sendRedirect(Constants.URL_LOGIN);
			System.out.println(Constants.URL_LOGIN);
            return false;
		}
		return super.preHandle(request, response, handler);
	}
}
