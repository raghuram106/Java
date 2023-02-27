package com.tweetapp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TweetLengthExceptionTest {
	TweetLengthException lengthException = new TweetLengthException();

	@Test
	public void PasswordTest() {
		assertThat(lengthException).isNotNull();
	}

}
