package com.full.dao;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.full.bean.ProfileSubmissionPercentage;
import com.full.helper.PMF;

public class PercentageTrackerDao {
	public void add(ProfileSubmissionPercentage percent){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
            pm.makePersistent(percent);
            tx.commit();
        } finally {
            pm.close();
        }
	}
	
	public ProfileSubmissionPercentage get(String email){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProfileSubmissionPercentage percent = pm.getObjectById(ProfileSubmissionPercentage.class,email);
		pm.close();
		return percent;
	}
	
	public void delete(String email){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ProfileSubmissionPercentage percent = pm.getObjectById(ProfileSubmissionPercentage.class,email);
		pm.deletePersistent(percent);
		pm.close();
	}
}
