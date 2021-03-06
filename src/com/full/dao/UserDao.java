package com.full.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.full.bean.User;
import com.full.helper.PMF;

public class UserDao {
	public boolean doesUserExist(String email){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user = null;
		email = email.toLowerCase();
		
		try {
			user = pm.getObjectById(User.class, email);
		}
		catch(Exception e){
			return false;
		}
		finally {
			pm.close();
		}
		return true;
	}
	
	public void addUser(User user){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
            pm.makePersistent(user);
            tx.commit();
        } finally {
            pm.close();
        }
	}
	
	public User getUser(String email){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user = null;
		try {
			user = pm.getObjectById(User.class, email.toLowerCase());
        } finally {
            pm.close();
        }
		return user;
	}
	public void delete(String email){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user = null;
		try {
			user = pm.getObjectById(User.class, email.toLowerCase());
			pm.deletePersistent(user);
        } finally {
            pm.close();
        }
	}
	public boolean update(User user, String email){
		delete(email);
		addUser(user);
		return true;
		
	}
}
