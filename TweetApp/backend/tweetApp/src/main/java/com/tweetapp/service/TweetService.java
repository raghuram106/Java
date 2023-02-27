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
	

	private static int id = 0;


	@Autowired
	TweetRepo tweetRepo;

	public List<Tweet> allTweets() {
		// TODO Auto-generated method stub
		return tweetRepo.findAll();
	}

	public Tweet postTweet(Tweet tweet) throws TweetLengthException, InvalidUserException {
		// TODO Auto-generated method stub
		
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
		
		return tweet;
	}

	public List<Tweet> searchTweet(String username) {
		// TODO Auto-generated method stub
		List<Tweet> tweets = tweetRepo.findAll();
		List<Tweet> tweetSearch = new ArrayList<Tweet>();
		for (Tweet i : tweets)
			if (i.getLoginId().equals(username)) {
				tweetSearch.add(i);
			}
		return tweetSearch;
	}

	public Tweet updateTweet(Tweet t) throws InvalidUserException {
		// TODO Auto-generated method stub
		deleteTweet(t.getLoginId(),t.getTweetId());
		tweetRepo.save(t);
		return t;
	}

	public Tweet deleteTweet(String username,int id) throws InvalidUserException {
		// TODO Auto-generated method stub
		Tweet t = findById(id);
		if(username.equals(t.getLoginId())) {
			tweetRepo.delete(t);
		}
		else {
			throw new InvalidUserException();
		}
		return t;

	}

	public Tweet likeTweet(String username, int id) {
		Tweet t = findById(id);
		List<String> likedUsers = t.getLikedUsers();
		long x = t.getLikeCount();
		if(likedUsers.contains(username)) {
			t.setLikeCount(x-1);
			likedUsers.remove(username);
			t.setLikedUsers(likedUsers);
			tweetRepo.save(t);
		}
		else {
			t.setLikeCount(x+1);
			likedUsers.add(username);
			t.setLikedUsers(likedUsers);
			tweetRepo.save(t);
		}
		return t;
	}

	public Tweet replyTweet(String username, int id, String message) throws TweetLengthException {
		if(message.length()>144) {
			throw new TweetLengthException();
		}
		Tweet t = findById(id);
		List<String> r = t.getReply();
		String msg = username + " : " + message;
		r.add(msg);
		tweetRepo.save(t);
		return t;
	}

	public Tweet findById(int id) {
		List<Tweet> tweets = tweetRepo.findAll();
		for (Tweet t : tweets) {
			if (t.getTweetId() == id) {
				return t;
			}
		}
		return new Tweet();

	}

	public Tweet getTweet(int id) {
		// TODO Auto-generated method stub
		Tweet t = findById(id);
		return t;
	}

}
