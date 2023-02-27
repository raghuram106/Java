package com.cognizant.mfpe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
@SpringBootTest
class PolicyRepoTest {

	PolicyRepository policyRepo;
	@Test
	void test() {
		assertThat(policyRepo).isNull();
	}

}
