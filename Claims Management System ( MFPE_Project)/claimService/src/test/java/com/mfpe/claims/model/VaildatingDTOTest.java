package com.mfpe.claims.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidatingDTOTest {
	VaildatingDTO validatingDTO=new VaildatingDTO();
	
	@Test
	void validatingDTOIsLoadingOrNot() {
		assertThat(validatingDTO).isNotNull();
	}
	
	@Test
	void checkValidatingDTO() {
		VaildatingDTO validatingDTO=new VaildatingDTO(true);
		
		validatingDTO.setValidStatus(false);
		
		assertEquals(false, validatingDTO.isValidStatus());
	}
}
