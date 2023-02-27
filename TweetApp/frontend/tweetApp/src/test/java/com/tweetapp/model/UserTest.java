package com.tweetapp.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

	User user = new User();

	@Test
	void UserIsWorkingOrNot() {
		assertThat(user).isNotNull();
	}

	@Test
	void testingUser() throws ParseException {
		user = new User("mani", "ch", "mani", "mani@gmail.com", "sai", "sai", "1234567890");
		user.setConfirmPassword("sai");
		user.setFirstName("ch");
		user.setContactNumber("9087654321");
		user.setEmail("mani@dd.sm");
		user.setPassword("sai");
		user.setLastName("maneesha");
		assertNotEquals("manisha", user.getLoginId());
		assertEquals("sai", user.getConfirmPassword());
		assertEquals("sai", user.getPassword());
		assertEquals("ch", user.getFirstName());
		assertEquals("9087654321", user.getContactNumber());
		assertEquals("maneesha", user.getLastName());
		assertEquals("mani@dd.sm", user.getEmail());

	}

}
