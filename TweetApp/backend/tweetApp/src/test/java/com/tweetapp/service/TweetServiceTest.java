package com.tweetapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepo;

@SpringBootTest
public class TweetServiceTest {

	@Mock
	TweetRepo tweetRepo;

	@InjectMocks
	TweetService tweetService;

	@Test
	void tweetsTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		List<Tweet> tweets = tweetService.allTweets();
		assertThat(tweets).isNotNull();
	}

	@Test
	void tweetsDeleteTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Assertions.assertThrows(InvalidUserException.class, ()->tweetService.deleteTweet("sai",2));

		assertEquals("sai", t.getLoginId());
	}

	@Test
	void tweetsReplyTest() throws TweetLengthException {
		
		Tweet t = new Tweet(2, "hi", "sai", new Date(),  null, 1);
		tweetRepo.save(t);
		//Tweet t1 = tweetService.replyTweet("sai", 2, "hello sai");
		assertEquals("hi",t.getTweetMessage());
	}

	@Test
	void tweetsPostTest() throws TweetLengthException, InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(3, "hi", "sai", new Date(), r, 1);
		Tweet tweet = tweetService.postTweet(t);
		assertEquals(t.getTweetId(), 3);
	}

	@Test
	void tweetsLikeTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		tweetService.likeTweet("sai", 2);
		assertEquals(t.getLikeCount(),1 );
	}

	@Test
	void getTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		tweetService.getTweet(2);
		assertThat(t).isNotNull();
	}

	@Test
	void updateTweetTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet t1 = new Tweet(2, "hi sai", "sai", new Date(), r, 1);
		Assertions.assertThrows(InvalidUserException.class, ()->tweetService.deleteTweet("sai",2));
	}

	@Test
	void searchTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		List<Tweet> tweet = tweetService.searchTweet("sai");
		assertEquals("hi",t.getTweetMessage());
	}

	@Test
	void deleteTweetExceptionTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Assertions.assertThrows(InvalidUserException.class, ()->tweetService.deleteTweet("sai",2));

	}

	@Test
	void replyTweetExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetService.replyTweet("sai", 2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	void postTweetExceptionTest() throws TweetLengthException, InvalidUserException {

		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(-1, "hi", "sai", new Date(), r, 1);
		Tweet t1 = tweetService.postTweet(t);
		assertEquals(t.getLoginId(), "sai");

	}

	@Test
	void postTweetMessageExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("sai:hi");
		Tweet t = new Tweet(2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				"sai", new Date(), r, 1);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetService.postTweet(t));
	}

}
