package com.mfpe.claims.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PolicyExceptionTest {

PolicyException policyException=new PolicyException("Exception");
	
	@Test
	void policyExceptionIsLoadingOrNot() {
		assertThat(policyException).isNotNull();
	}
}
