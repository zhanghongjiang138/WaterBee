package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AjaxTestController {
	
	
	@RequestMapping(value="user_login_confirm_htm",method=RequestMethod.POST)
	public String ajaxProcess(HttpServletRequest req,HttpServletResponse res){
		HashMap<String,Object> map=new HashMap();
		String username=req.getParameter("username");
		map.put("username",username);
		req.setAttribute("model", map);
		return "loing_success";

	}
	
	

}
