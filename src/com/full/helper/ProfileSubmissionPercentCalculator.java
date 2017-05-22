package com.full.helper;

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
		if(user.getCompany()!=null && user.getCompany().getCompanyName()!=null){
			percent += companyNameWeightage;
			if(user.getCompany().getCompanyAddress()!=null && user.getCompany().getCompanyAddress().isNeedToPersist())
				percent += companyAddressWeightage;
		}
		return percent;
	}
	
}
