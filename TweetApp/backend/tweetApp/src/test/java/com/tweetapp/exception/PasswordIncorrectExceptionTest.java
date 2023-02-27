package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordIncorrectExceptionTest {
	
PasswordIncorrectException passwordException = new PasswordIncorrectException();
	
	@Test
	public void PasswordTest() {
		assertThat(passwordException).isNotNull();
	}
	

}
