package com.cognizant.mfpe.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BenefitsTest {
	
	Benefits benefit = new Benefits();

	@Test
	 void policyIsWorkingOrNot() {
	       assertThat(benefit).isNotNull();
	  }

	 @Test
	  void testingPolicy() throws ParseException{
	    	benefit=new Benefits(1,"b1","Life Time");
	    	benefit.setId(8);
	    	benefit.setBid("b6");
	    	benefit.setBenifit("Life Time");
	        assertEquals(8,benefit.getId());
	        assertEquals("b6",benefit.getBid());
	        assertEquals("Life Time",benefit.getBenifit());
	      
	    }
	  	
		
	  
}
