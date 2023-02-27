package com.cognizant.mfpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.mfpe.model.Benefits;
import com.cognizant.mfpe.model.MemberPolicy;
import com.cognizant.mfpe.model.Policy;
import com.cognizant.mfpe.model.ProviderPolicy;
import com.cognizant.mfpe.repository.BenefitRepository;
import com.cognizant.mfpe.repository.MemberPolicyRepository;
import com.cognizant.mfpe.repository.PolicyRepository;
import com.cognizant.mfpe.repository.ProviderPolicyRepository;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class PolicyServiceApplication implements CommandLineRunner {

	@Autowired
	BenefitRepository brepo;
	
	@Autowired
	MemberPolicyRepository mrepo;
	
	@Autowired
	PolicyRepository prepo;
	
	@Autowired
	ProviderPolicyRepository prorepo;
	public static void main(String[] args) {
		SpringApplication.run(PolicyServiceApplication.class, args);
	}
	
public void run(String... args) throws Exception{
		
		Policy p1 = new Policy(1,"Health Plus Classic",500000,15000);
		Policy p2 = new Policy(2,"Health Plus Enhanced",3000000,20000);
		Policy p3 = new Policy(3,"Health Plus Premium",10000000,25000);
		
		prepo.save(p1);
		prepo.save(p2);
		prepo.save(p3);
		
		ProviderPolicy pro1 = new ProviderPolicy("h1",1,"Apollo Hospital","Hyderabad");
		ProviderPolicy pro2 = new ProviderPolicy("h2",2,"KIMS Hospital","Srikakulam");
		ProviderPolicy pro3 = new ProviderPolicy("h3",3,"KGH","Hyderabad");
		ProviderPolicy pro4 = new ProviderPolicy("h4",1,"Ujwala Hospital","Vizag");
		ProviderPolicy pro5 = new ProviderPolicy("h5",2,"Vijay Hospital","Delhi");
		ProviderPolicy pro6 = new ProviderPolicy("h6",1,"Indus","Srikakulam");
		
		prorepo.save(pro1);
		prorepo.save(pro2);
		prorepo.save(pro3);
		prorepo.save(pro4);
		prorepo.save(pro5);
		prorepo.save(pro6);
		
		
		Benefits b1 = new Benefits(1,"b1","Coverage for COVID-19");
		Benefits b2 = new Benefits(2,"b1","Coverage for hospitalization at home");
		Benefits b3 = new Benefits(3,"b2","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits(4,"b4","Life Time");
		Benefits b5 = new Benefits(5,"b2","Ambulance charges upto 4000 covered");
		Benefits b6 = new Benefits(6,"b1","Hospitalization charges for Premium room covered");
		Benefits b7 = new Benefits(7,"b3","Hospitalization charges for Deluxe room covered");
		
		brepo.save(b1);
		brepo.save(b2);
		brepo.save(b3);
		brepo.save(b4);
		brepo.save(b5);
		brepo.save(b6);
		brepo.save(b7);

		
		
		MemberPolicy m1 = new MemberPolicy("m1",1,"b1","vijay",32,"male","talavaram","10/11/2020",10);
		MemberPolicy m2 = new MemberPolicy("m2",2,"b2","bhargav",26,"male","palakonda","20/1/2021",8);
		MemberPolicy m3 = new MemberPolicy("m3",1,"b3","mani",22,"female","srikakulam","14/4/2021",12);
		MemberPolicy m4 = new MemberPolicy("m4",2,"b4","sai",18,"male","vizag","19/10/2021",10);
		
		mrepo.save(m1);
		mrepo.save(m2);
		mrepo.save(m3);
		mrepo.save(m4);

		

	}

}
