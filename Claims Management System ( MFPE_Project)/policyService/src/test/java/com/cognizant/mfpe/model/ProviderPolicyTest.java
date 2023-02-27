package com.cognizant.mfpe.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderPolicyTest {
	
	ProviderPolicy provider = new ProviderPolicy();
	
	@Test
	 void providerPolicyIsWorkingOrNot() {
	       assertThat(provider).isNotNull();
	  }

	  @Test
	  void testingPolicy() throws ParseException{
	    	provider=new ProviderPolicy("h4",3,"KGH","Vizag");
	    	provider.setPid(2);
	        provider.setHospitalId("h2");
	        provider.setHospitalName("kims");
	        provider.setLocation("vizag");
	        assertEquals("h2",provider.getHospitalId());
	        assertEquals(2,provider.getPid());
	        assertEquals("kims",provider.getHospitalname());
	        assertEquals("vizag",provider.getLocation());
	     
	    }
}
