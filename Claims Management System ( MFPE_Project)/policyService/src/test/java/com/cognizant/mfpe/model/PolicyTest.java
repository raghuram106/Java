package com.cognizant.mfpe.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PolicyTest {
	
	Policy policy = new Policy();

	 @Test
	 void policyIsWorkingOrNot() {
	       assertThat(policy).isNotNull();
	  }

	  @Test
	  void testingPolicy() throws ParseException{
	    	policy=new Policy(2,"Health",10000,30000);
	    	policy.setPid(1);
	        policy.setPtype("Health Policy");
	        policy.setCapamount(100000);
	        policy.setPremium(20000);
	       
	        assertEquals(1,policy.getPid());
	        assertEquals("Health Policy",policy.getPtype());
	        assertEquals(100000,policy.getCapamount());
	        assertEquals(20000,policy.getPremium());
	     
	    }
}
