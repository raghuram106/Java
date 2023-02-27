package com.tweetapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepo;

@SpringBootTest
public class TweetServiceTest {

	@Autowired
	TweetRepo tweetRepo;

	@Autowired
	TweetService tweetService;

	@Test
	void tweetsTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		List<Tweet> tweets = tweetService.allTweets();
		assertThat(tweets).isNotNull();
	}

	@Test
	void tweetsDeleteTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet tweet = tweetService.deleteTweet("sai",2);

		assertEquals("sai", tweet.getLoginId());
	}

	@Test
	void tweetsReplyTest() throws TweetLengthException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet tweet = tweetService.replyTweet("sai", 2, "hello sai");
		assertThat(tweet.getReply()).isNotNull();
	}

	@Test
	void tweetsPostTest() throws TweetLengthException, InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(3, "hi", "sai", new Date(), r, 1);
		Tweet tweet = tweetService.postTweet(t);
		assertEquals(t.getTweetId(), tweet.getTweetId());
	}

	@Test
	void tweetsLikeTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet x = tweetService.likeTweet("sai", 2);
		assertThat(x.getLikeCount() > 1);
	}

	@Test
	void getTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet tweet = tweetService.getTweet(2);
		assertEquals("hi", tweet.getTweetMessage());
	}

	@Test
	void updateTweetTest() throws InvalidUserException {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		Tweet t1 = new Tweet(2, "hi sai", "sai", new Date(), r, 1);
		Tweet tweet = tweetService.updateTweet(t1);
		assertEquals("hi sai", tweet.getTweetMessage());
	}

	@Test
	void searchTweetTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);
		List<Tweet> tweet = tweetService.searchTweet("sai");
		assertThat(tweet).isNotEmpty();
	}

	@Test
	void deleteTweetExceptionTest() throws InvalidUserException {
		Tweet t = tweetService.deleteTweet("sai",1);
		assertThat(t).isNotNull();
	}

	@Test
	void replyTweetExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2, "hi", "sai", new Date(), r, 1);
		tweetRepo.save(t);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetService.replyTweet("sai", 2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}

	@Test
	void postTweetExceptionTest() throws TweetLengthException, InvalidUserException {

		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(-1, "hi", "sai", new Date(), r, 1);
		Tweet t1 = tweetService.postTweet(t);
		assertEquals(t.getLoginId(), t1.getLoginId());

	}

	@Test
	void postTweetMessageExceptionTest() {
		List<String> r = new ArrayList<String>();
		r.add("hi");
		Tweet t = new Tweet(2,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				"sai", new Date(), r, 1);

		Assertions.assertThrows(TweetLengthException.class, () -> tweetService.postTweet(t));
	}

}
