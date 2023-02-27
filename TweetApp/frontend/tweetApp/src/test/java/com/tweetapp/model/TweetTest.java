package com.tweetapp.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TweetTest {

	Tweet tweet = new Tweet();
	Date date = new Date();

	@Test
	void TweetIsWorkingOrNot() {
		assertThat(tweet).isNotNull();
	}

	@Test
	void testingUser() throws ParseException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		tweet = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweet.setLoginId("mani");
		tweet.setLikeCount(1);
		tweet.setTweetTime(new Date());
		tweet.setTweetMessage("hi");
		tweet.setTweetId(1);
		r.add("hello");
		tweet.setReply(r);
		assertEquals("mani", tweet.getLoginId());
		assertEquals(1, tweet.getLikeCount());
		assertEquals(1, tweet.getTweetId());
		assertEquals(2, tweet.getReply().size());
		assertNotEquals(22 - 01 - 22, tweet.getTweetTime());
		assertEquals(2, tweet.getTweetMessage().length());

	}

}
