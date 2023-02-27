package com.tweetapp.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.repository.AuthRequestRepo;

@SpringBootTest
public class AuthRequestRepoTest {
	
	AuthRequestRepo authRepo;
	
	@Test
	void test() {
		assertThat(authRepo).isNull();
	}

}
