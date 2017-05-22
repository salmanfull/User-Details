package com.full.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.full.bean.ProfileSubmissionPercentage;
import com.full.bean.User;
import com.full.dao.PercentageTrackerDao;
import com.full.helper.ProfileSubmissionPercentCalculator;

public class PercentageTrackerService {
		
		private PercentageTrackerDao dao;
		@Autowired
	    public void setDao(PercentageTrackerDao dao) {
			this.dao = dao;
		}
		public void add(User user){
	    	String email = user.getEmail();
	    	ProfileSubmissionPercentage percent = new ProfileSubmissionPercentage();
			percent.setEmail(email);
			percent.setPercent(ProfileSubmissionPercentCalculator.percentCalculator(user));
			dao.add(percent);
	    }
	    public int get(String email){
	    	ProfileSubmissionPercentage percent = dao.get(email.toLowerCase());
	    	return percent.getPercent();
	    }
	    
	    public boolean update(String oldEmail,User user){
	    	delete(oldEmail);
	    	add(user);
	    	return true;
	    }
	    
	    public void delete(String email){
	    	dao.delete(email.toLowerCase());
	    }
}
