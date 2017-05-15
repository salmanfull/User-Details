package com.full;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
	 private UserDao dao;
	     
	    public SearchController(){
	    }
	    
	    @Autowired
		public void setDao(UserDao dao) {
			this.dao = dao;
		}
	    
	    @RequestMapping("/searchuser")
		public String getUser(){
			return "search";
		}
	    
	    @RequestMapping(value = "/search", method = RequestMethod.POST)
	    public @ResponseBody User search(@RequestParam("email") String email, HttpServletResponse resp) throws IOException{
	    	if(!dao.doesUserExist(email)){
	    		resp.getWriter().print("null");
	    		return null;
	    	}
	    	return dao.getUser(email);
	    }
}
