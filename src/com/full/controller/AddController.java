package com.full.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.bean.Address;
import com.full.bean.User;
import com.full.dao.UserDao;
import com.full.service.PercentageTrackerService;

@Controller
public class AddController {
    private UserDao userDao;
    @Autowired 
    private PercentageTrackerService percentageService;
    
    
    public AddController(){
    }
    
    @Autowired
	public void setDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping("/adduser")
	public String adduser(){
		return "adduser";
	}
    
    @RequestMapping(value="/user", consumes="application/json", method=RequestMethod.POST)
	public @ResponseBody boolean addUserToDB(@RequestBody User user){
		String email = user.getEmail();
    	if(userDao.doesUserExist(email)){
			return false;
		}
    	else{
    		Address companyAddress = user.getCompany().getCompanyAddress();
    		if(user.getCompany().getCompanyName()==null && !companyAddress.isNeedToPersist()){
    			user.setCompany(null);
    		}
    		else if(!companyAddress.isNeedToPersist())
    			user.getCompany().setCompanyAddress(null);
    		userDao.addUser(user);
    		percentageService.add(user);
    		return true;
    	}
	}
    
}
