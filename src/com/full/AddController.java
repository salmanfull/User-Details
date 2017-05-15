package com.full;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddController {
    private UserDao dao;
    
    
    public AddController(){
    }
    
    @Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/adduser")
	public String adduser(){
		return "adduser";
	}
    
    @RequestMapping(value="/user", consumes="application/json", method=RequestMethod.POST)
	public @ResponseBody boolean addUserToDB(@RequestBody User user){
		String email = user.getEmail();
    	if(dao.doesUserExist(email)){
			return false;
		}
    	else{
    		if(user.getCompany().getCompanyName().length()==0 && user.getCompany().getCompanyAddress().length()==0){
    			user.setCompany(null);
    		}
    		dao.addUser(user);
    		return true;
    	}
	}
    
}
