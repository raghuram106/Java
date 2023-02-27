package com.mfpe.claims.exception;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class InvalidClaimIdExceptionTest {

	InvalidClaimIdException invalidClaimIdException=new InvalidClaimIdException("Exception");
	
	@Test
	void invalidClaimIdExceptionIsLoadingOrNot() {
		assertThat(invalidClaimIdException).isNotNull();
	}
}

