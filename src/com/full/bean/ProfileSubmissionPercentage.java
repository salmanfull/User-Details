package com.full.bean;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ProfileSubmissionPercentage {
		@Persistent
		@PrimaryKey
		private String email;
		@Persistent
		private int percent;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email.toLowerCase();
		}
		public int getPercent() {
			return percent;
		}
		public void setPercent(int percent) {
			this.percent = percent;
		}
		
}
