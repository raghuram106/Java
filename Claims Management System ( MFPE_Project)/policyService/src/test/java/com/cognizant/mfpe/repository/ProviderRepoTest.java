package com.cognizant.mfpe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProviderRepoTest {
	
	ProviderPolicyRepository bRepo;

	@Test
	void test() {
		assertThat(bRepo).isNull();
	}

}
