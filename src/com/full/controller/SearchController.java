package com.full.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.dao.UserDao;
import com.full.service.PercentageTrackerService;

@Controller
public class SearchController {
	 private UserDao dao;
	 
	 private PercentageTrackerService percentageService;
	     
	    public SearchController(){
	    }
	    
	    @Autowired
	    public void setPercentageService(PercentageTrackerService percentageService) {
			this.percentageService = percentageService;
		}


		@Autowired
		public void setDao(UserDao dao) {
			this.dao = dao;
		}
	    
	    @RequestMapping("/searchuser")
		public String getUser(){
			return "search";
		}
	    
	    @RequestMapping(value = "/user/{email:.+}", method = RequestMethod.GET)
	    public ResponseEntity<?> search(@PathVariable String email, HttpServletResponse resp) throws IOException{
	    	if(!dao.doesUserExist(email)){
	    		resp.getWriter().print("null");
	    		return null;
	    	}
	    	Map<String,Object> map = new LinkedHashMap<String, Object>();
	    	map.put("user", dao.getUser(email) );
	    	map.put("percent", percentageService.get(email));
	    	return new ResponseEntity<Map<String, Object> >(map, HttpStatus.OK);
	    }
}
