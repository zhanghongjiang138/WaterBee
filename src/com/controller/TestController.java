package com.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.TestDao;

@Controller
public class TestController {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private TestDao testDao;
	

	
	@RequestMapping(value="/user_login_confirm",method={RequestMethod.POST,RequestMethod.GET})
	public String ajaxProcess(HttpServletRequest req,HttpServletResponse res){
		HashMap<String,Object> map=new HashMap();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		map.put("username",username);
		map.put("password",password);
		testDao.getList( "select mdl_ver_id from cost_model_ver", map, this.getClass());
		List l=hibernateTemplate.find("select mdl_ver_id from cost_model_ver");
		Iterator iter=l.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.print("\n  model ");
		req.setAttribute("model", map);
		return "success";

	}
	
	

}
