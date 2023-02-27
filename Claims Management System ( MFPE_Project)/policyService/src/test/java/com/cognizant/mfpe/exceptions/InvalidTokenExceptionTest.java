package com.cognizant.mfpe.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidTokenExceptionTest {
	InvalidMemberIdException invalidTokenException = new InvalidMemberIdException("Exception");
	@Test
	 void invalidTokenExceptionIsLoadingOrNot() {
		
		assertThat(invalidTokenException).isNotNull(); 
	}
	
}


	