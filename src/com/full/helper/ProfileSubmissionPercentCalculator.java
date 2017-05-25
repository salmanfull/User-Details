package com.full.helper;

import com.full.bean.Address;
import com.full.bean.User;

public class ProfileSubmissionPercentCalculator {
	private static int nameWeightage=20;
	
	private static int phoneWeightage=20;
	
	private static int emailWeightage=20;
	
	private static int companyNameWeightage=20;
	
	private static int companyAddressWeightage=20;
	
	public static int percentCalculator(User user){
		int percent = 0;
		if(user.getName()!="")
			percent += nameWeightage;
		if(user.getEmail()!="")
			percent += emailWeightage;
		if(user.getPhone()!="")
			percent += phoneWeightage;
		if(user.getCompany()!=null){
			if(user.getCompany().getCompanyName()!=null)
			 percent += companyNameWeightage;
			if(user.getCompany().getCompanyAddress()!=null && user.getCompany().getCompanyAddress().isNeedToPersist())
				percent += addressWeightage(user.getCompany().getCompanyAddress());
		}
		return percent;
	}
	private static int addressWeightage(Address address){
		int fieldWeight = companyAddressWeightage/5;
		int percent=0;
		if(address.getAddress1()!=null || address.getAddress2()!=null)
			percent+= fieldWeight;
		if(address.getCity()!=null)
			percent+= fieldWeight;
		if(address.getCountry()!=null)
			percent+= fieldWeight;
		if(address.getState()!=null)
			percent+= fieldWeight;
		if(address.getZipcode()!=null)
			percent+= fieldWeight;
		return percent;
	}
	
}
