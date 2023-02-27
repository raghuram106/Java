package com.cognizant.mfpe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cognizant.mfpe.model.Benefits;
import com.cognizant.mfpe.model.MemberPolicy;
import com.cognizant.mfpe.repository.BenefitRepository;
import com.cognizant.mfpe.repository.MemberPolicyRepository;
import com.cognizant.mfpe.repository.PolicyRepository;

@SpringBootTest
class MemberserviceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberPolicyRepository mrepo;
	
	@Autowired
	BenefitRepository brepo;

	@Autowired
	PolicyRepository prepo;

	@Test
	void test() {
		assertThat(memberService).isNotNull();
	}
	
	@Test
	void testBenefit() {
		Benefits b = new Benefits(1,"b1","Life Time");
		memberService.addBenefit(b);
	}
	
	@Test
	void testMember() {
		MemberPolicy m = new MemberPolicy("m1",1,"b1","vijay",32,"male","talavaram","10/11/2020",10);
		memberService.addMember(m);
				
	}
	

	@Test
	void BenefitsTest() {
		Benefits benefit1=new Benefits(1,"b1","Life Time");
		Benefits benefit2=new Benefits(2,"b1","10 years");
		brepo.save(benefit1);
		brepo.save(benefit2);

		MemberPolicy m = new MemberPolicy("m1",1,"b1","vijay",32,"male","talavaram","10/11/2020",10);
		mrepo.save(m);
		
		List<String> list = new ArrayList<>();
				list.add("Life Time");
				list.add("10 years");
				
		List<String> list1 = memberService.getBenefits(1,"m1");

		assertNotEquals(list,list1);
		
		
	}
	
	
}
