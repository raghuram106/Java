package com.cognizant.mfpe.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PolicyIdExceptionTest  {

InvalidPolicyIdException policyException=new InvalidPolicyIdException("Exception");
	
	@Test
	@DisplayName("Checking if PolicyException class is loading or not")
	void policyExceptionIsLoadingOrNot() {
		assertThat(policyException).isNotNull();
	}
}