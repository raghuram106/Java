package com.tweetapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepo;

@Service
public class TweetService {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TweetService.class);

	private static int id = 0;


	@Autowired
	TweetRepo tweetRepo;

	public List<Tweet> allTweets() {
		// TODO Auto-generated method stub
		log.info("BEGIN - [All Tweets Service]");
		log.info("END - [All Tweets Servcie]");
		return tweetRepo.findAll();
	}

	public Tweet postTweet(Tweet tweet) throws TweetLengthException, InvalidUserException {
		// TODO Auto-generated method stub
		
		log.info("BEGIN - [Post Tweet Service]");
		if(tweet.getTweetMessage().length()>144) {
			throw new TweetLengthException();
		}
		if (tweet.getTweetId() == -1 || tweet.getTweetId() == 0) {
			id += 1;
			tweet.setTweetId(id);
			tweetRepo.save(tweet);
		} else {
			//deleteTweet(tweet.getLoginId(),tweet.getTweetId());
			tweetRepo.save(tweet);
		}
		log.info("END - [Post Tweet Service]");

		return tweet;
	}

	public List<Tweet> searchTweet(String username) {
		// TODO Auto-generated method stub
		log.info("BEGIN - [Search Tweet Service]");
		List<Tweet> tweets = tweetRepo.findAll();
		List<Tweet> tweetSearch = new ArrayList<Tweet>();
		for (Tweet i : tweets)
			if (i.getLoginId().equals(username)) {
				tweetSearch.add(i);
			}
		log.info("END - [Search Tweet Service]");
		return tweetSearch;
	}

	public Tweet updateTweet(Tweet t) throws InvalidUserException {
		// TODO Auto-generated method stub
		log.info("BEGIN - [Update Tweet Service]");
		deleteTweet(t.getLoginId(),t.getTweetId());
		tweetRepo.save(t);
		log.info("END - [Update Tweet Service]");
		return t;
	}

	public Tweet deleteTweet(String username,int id) throws InvalidUserException {
		// TODO Auto-generated method stub
		log.info("BEGIN - [Delete Tweet Service]");
		Tweet t = findById(id);
		if(username.equals(t.getLoginId())) {
			tweetRepo.delete(t);
		}
		else {
			throw new InvalidUserException();
		}
		log.info("END - [Delete Tweet Service]");
		return t;

	}

	public Tweet likeTweet(String username, int id) {
		log.info("BEGIN - [Like Tweet Service]");
		Tweet t = findById(id);
		Set<String> likedUsers = t.getLikedUsers();
		if(likedUsers.add(username)) {
			long x = t.getLikeCount();
			t.setLikeCount(x+1);
			tweetRepo.save(t);
		}
		log.info("END - [Like Tweet Service]");
		return t;
	}

	public Tweet replyTweet(String username, int id, String message) throws TweetLengthException {
		log.info("BEGIN - [Reply Tweet Service]");
		if(message.length()>144) {
			throw new TweetLengthException();
		}
		Tweet t = findById(id);
		List<String> r = t.getReply();
		String msg = username + " : " + message;
		r.add(msg);
		tweetRepo.save(t);
		log.info("END - [Reply Tweet Service]");
		return t;
	}

	public Tweet findById(int id) {
		log.info("BEGIN - [Find Tweet Service]");
		List<Tweet> tweets = tweetRepo.findAll();
		for (Tweet t : tweets) {
			if (t.getTweetId() == id) {
				return t;
			}
		}
		log.info("END - [Find Tweet Service]");
		return new Tweet();

	}

	public Tweet getTweet(int id) {
		// TODO Auto-generated method stub
		log.info("BEGIN - [Get Tweet Service]");
		Tweet t = findById(id);
		log.info("END - [Get Tweet Service]");
		return t;
	}

}
