package com.tweetapp.dto;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.dto.ValidatingDTO;

@SpringBootTest
public class ValidatingDTOTest {
	
	ValidatingDTO validatingStatus = new ValidatingDTO();

	@Test
	public void validatingTest() {
		validatingStatus = new ValidatingDTO(true);
		validatingStatus.setValidStatus(false);
		assertNotEquals(true,validatingStatus.isValidStatus());
	}

}
