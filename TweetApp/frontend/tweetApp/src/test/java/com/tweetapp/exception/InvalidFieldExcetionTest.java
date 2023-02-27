package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidFieldExcetionTest {
	
InvalidFieldException invalidFieldException = new InvalidFieldException();
	
	@Test
	public void EmailTest() {
		assertThat(invalidFieldException).isNotNull();
	}

}
