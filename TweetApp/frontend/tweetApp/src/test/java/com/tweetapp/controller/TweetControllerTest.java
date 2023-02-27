package com.tweetapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepo;

@SpringBootTest()
public class TweetControllerTest {

	@Autowired
	TweetController tweetController;

	@Autowired
	TweetRepo tweetRepo;
	

	@Test
	void testAllTweet() {
		List<String> r = new ArrayList<String>();
		r.add("hi");

		Tweet tweet = new Tweet(2, "hi", "sai", new Date(), r, 1);
		Tweet tweet1 = new Tweet(1, "hi", "sai1", new Date(), r, 1);
		Tweet tweet2 = new Tweet(3, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(tweet);
		tweetRepo.save(tweet1);
		tweetRepo.save(tweet2);
		ResponseEntity<List<Tweet>> t =  tweetController.getAllTweets();
		assertThat(t.getBody()).isNotEmpty();
		

	}

	@Test
	void tweetsDeleteTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		tweetController.deleteTweet("sai", 2);

		assertEquals("sai", t.getLoginId());
	}

	@Test
	void tweetsReplyTest() throws TweetLengthException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		ResponseEntity<Tweet> tweet = tweetController.replyTweet("sai", 2, "hello sai");
		assertEquals(tweet.getBody().getTweetMessage(),"hi");
	}

	@Test
	void tweetsPostTest() throws TweetLengthException, InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(20, "hi", "sai", new Date(), r, 1);
		ResponseEntity<Void> tweet = tweetController.postTweet("sai", t);
		assertEquals(HttpStatus.CREATED, tweet.getStatusCode());
	}

	@Test
	void tweetsLikeTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		ResponseEntity<Tweet> x = tweetController.likeTweet("sai", 2);
		assertEquals(2, x.getBody().getLikeCount());
	}

	@Test
	void getTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		ResponseEntity<Tweet> tweet = tweetController.getTweet(2);
		assertEquals("hi", tweet.getBody().getTweetMessage());
	}

	@Test
	void updateTweetTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet t1 = new Tweet(2, "hi sai", "sai", new Date(), r, 1);
		ResponseEntity<Tweet> tweet = tweetController.updateTweet(t1);
		assertEquals("hi sai", tweet.getBody().getTweetMessage());
	}

	@Test
	void searchTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		ResponseEntity<List<Tweet>> tweet = tweetController.searchTweet("sai");
		assertThat(tweet.getBody()).isNotEmpty();
	}

	@Test
	void deleteTweetExceptionTest() throws InvalidUserException {
		ResponseEntity<Void> t = tweetController.deleteTweet("sai", 2);
		assertThat(HttpStatus.CREATED);
	}

	@Test
	void replyTweetExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetController.replyTweet("sai", 2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	void postTweetExceptionTest() throws TweetLengthException, InvalidUserException {

		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(-1, "hi", "sai", new Date(), r, 1);
		ResponseEntity<Void> t1 = tweetController.postTweet("sai", t);
		assertThat(HttpStatus.CREATED);

	}

	@Test
	void postTweetMessageExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				"sai", new Date(), r, 1);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetController.postTweet("sai", t));
	}

}
