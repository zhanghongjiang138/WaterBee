package com.model.authenticationModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticationModel implements HandlerInterceptor{
	private static Logger logger = Logger.getLogger("com.model.authenticationModel.AuthenticationModel"); 
	public boolean authenticationUser (HttpServletRequest req,HttpServletResponse res){
		
		logger.info("authenticationUser running");
		
		
		return true;
		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		return authenticationUser(req,res);
	}

}
