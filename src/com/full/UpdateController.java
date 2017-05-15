package com.full;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpdateController {
	private UserDao dao;
     
    public UpdateController(){
    }
    
    @Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
    @RequestMapping("/update")
    public String getUser(){
		return "update";
	}
    
    @RequestMapping(value="/updateuser", consumes="application/json", method=RequestMethod.POST)
	public @ResponseBody boolean updateUser(@RequestBody User user,@RequestParam String email){
    	if(!email.equalsIgnoreCase(user.getEmail()) && dao.doesUserExist(user.getEmail()))
    		return false;
    	return dao.update(user,email);
    }
    
}
