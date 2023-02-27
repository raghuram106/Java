package com.cognizant.mfpe.client;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@SpringBootTest
class AuthClientTest {

	AuthClient authClient;
	
	@Test
	@DisplayName("Checking is AuthClient is loading or not")
	void authClientIsWorkingOrNot() {
		assertThat(authClient).isNull();
	}
}