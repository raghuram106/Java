package com.mfpe.claims.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class InvalidTokenExceptionTest {

	 InvalidTokenException  invalidTokenException=new  InvalidTokenException("Exception"); 
	 
	 @Test
	 void  invalidTokenExceptionIsLoadingOrNot() {
		 assertThat(invalidTokenException).isNotNull();
	 }
}
