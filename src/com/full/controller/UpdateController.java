package com.full.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.bean.Address;
import com.full.bean.User;
import com.full.dao.UserDao;
import com.full.service.PercentageTrackerService;

@Controller
public class UpdateController {
	private UserDao dao;
	@Autowired
	private PercentageTrackerService percentageService; 
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
    
    @RequestMapping(value="/updateuser/{email:.+}", consumes="application/json", method=RequestMethod.PUT)
	public @ResponseBody boolean updateUser(@RequestBody User user,@PathVariable String email){
    	if(!email.equalsIgnoreCase(user.getEmail()) && dao.doesUserExist(user.getEmail()))
    		return false;
    	Address companyAddress = user.getCompany().getCompanyAddress();
    	if(!companyAddress.isNeedToPersist())
			user.getCompany().setCompanyAddress(null);
    	return dao.update(user,email) && percentageService.update(email, user);
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    public @ResponseBody boolean deleteUser(@RequestParam String email){
    	if(dao.doesUserExist(email)){
    	dao.delete(email);
    	return true;
    	}
    	return false;
    }
}
