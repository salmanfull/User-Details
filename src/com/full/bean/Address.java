package com.full.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Address {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    public void setKey(Key key) {
        this.key = key;
    }
		@Persistent
		String address1;
		@Persistent
		String address2;
		@Persistent
		String city;
		@Persistent
		String state;
		@Persistent
		String country;
		@Persistent
		String zipcode;
		@NotPersistent
		private boolean needToPersist;
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			persistanceDecider(address1);
			this.address1 = address1;
		}
		public String getAddress2() {
			return address2;
		}
		public void setAddress2(String address2) {
			persistanceDecider(address2);
			this.address2 = address2;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			persistanceDecider(city);
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			persistanceDecider(state);
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			persistanceDecider(country);
			this.country = country;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			persistanceDecider(zipcode);
			this.zipcode = zipcode;
		}
		public boolean isNeedToPersist() {
			return needToPersist;
		}
		public void setNeedToPersist(boolean needToPersist) {
			this.needToPersist = needToPersist;
		}
		
		public void persistanceDecider(String input){
			if(!input.equals(""))
				needToPersist = true;
		}
		
}
