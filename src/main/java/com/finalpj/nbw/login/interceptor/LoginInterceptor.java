package com.finalpj.nbw.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.finalpj.nbw.login.dto.LoginDto;
import com.finalpj.nbw.login.exception.PasswordWrongException;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean logincheck = true;

		// 1. 로그인 상태인지검사
		if (request.getSession().getAttribute("UserLogin") != null) {
			// 1-1. 로그인 상태에서 요청시 에러 페이지로 이동
			logincheck = false;
			response.sendError(403, "이미 로그인한 사용자 입니다. ");
		} else {
			// 1-2. 로그인 상태가 아닐시 계속 진행
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("userPassword");
			
			System.out.println("Interceptor -> userId: "+userId);
			System.out.println("Interceptor -> userPassword: "+userPassword);
		}	
		return logincheck;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("postHandle");
		
		System.out.println(new PasswordWrongException("비밀번호가 틀렸습니다."));
		
	}
}
