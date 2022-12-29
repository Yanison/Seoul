package com.seoul.infra.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.seoul.infra.common.basic.Constants;

public class CheckLoginSessionInterception extends HandlerInterceptorAdapter{
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		/**
		 * preHandle :: 전 처리기, 클라이언트에서 컨트롤러로 요청할 때 가로체는 것
		 */
		if (request.getSession().getAttribute("memberSeq") == null) {
			response.sendRedirect(Constants.URL_LOGIN);
			System.out.println("preHandle");
			return false;
		} else {
			
		}
		
        return super.preHandle(request, response, handler);
    }
 
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        /**
         * postHandle :: 후처리기 컨트롤러에서 클라이언트로 요청할 때 가로체는 것
         */
		System.out.println("postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        /**
         * afterCompletion :: 컨트롤러의 처리가 끝나고 화면처리까지 모두 끝나면 실행되는 메서드. 
         */
    	System.out.println("afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }
    
}
