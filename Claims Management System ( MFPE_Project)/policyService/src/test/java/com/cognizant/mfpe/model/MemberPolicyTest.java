package com.cognizant.mfpe.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberPolicyTest {

	MemberPolicy member = new MemberPolicy();
	
	@Test
	 void policyIsWorkingOrNot() {
	       assertThat(member).isNotNull();
	  }

	  @Test
	  void testingPolicy() throws ParseException{
	    	member=new MemberPolicy("m1",3,"b1","sai",19,"male","palakonda","18/11/2021",10);
	    	member.setMid("m3");
	        member.setPid(1);
	        member.setBid("b2");
	        member.setMname("vijay");
	        member.setMage(32);
	        member.setMgender("male");
	        member.setMlocation("palakonda");
	        member.setSubdate("13/11/2021");
	        member.setMtenure(10);
	       
	        assertEquals("m3",member.getMid());
	        assertEquals(1,member.getPid());
	        assertEquals("b2",member.getBid());
	        assertEquals("vijay",member.getMname());
	        assertEquals(32,member.getMage());
	        assertEquals("male",member.getMgender());
	        assertEquals("palakonda",member.getMlocation());
	        assertEquals("13/11/2021",member.getSubdate());
	        assertEquals(10,member.getMtenure());
	       
	     
	    }

}
