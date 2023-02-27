package com.cognizant.mfpe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepoTest {
	
	MemberPolicyRepository mRepo;

	@Test
	void test() {
		assertThat(mRepo).isNull();
	}

}
