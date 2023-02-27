package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

	@Autowired
	TweetService tweetService;
	
	@GetMapping("/all/{id}")
	public ResponseEntity<Tweet> getTweet(@PathVariable("id") int id) {
		
		Tweet t = tweetService.getTweet(id);
		
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<Void> postTweet(@PathVariable("username") String username, @RequestBody Tweet tweet) throws TweetLengthException, InvalidUserException {
		
		Tweet t = tweetService.postTweet(tweet);
		
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/search/{username}")
	public ResponseEntity<List<Tweet>> searchTweet(@PathVariable("username") String username) {
		
		List<Tweet> tweets = tweetService.searchTweet(username);
		
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}

	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet tweet) throws InvalidUserException {
		
		Tweet t = tweetService.updateTweet(tweet);
		
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<Void> deleteTweet(@PathVariable("username") String username, @PathVariable("id") int id) throws InvalidUserException {
		
		Tweet t = tweetService.deleteTweet(username,id);
		if (t != null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<Tweet> likeTweet(@PathVariable("username") String username, @PathVariable("id") int id) {
		
		Tweet x = tweetService.likeTweet(username, id);
		
		return new ResponseEntity<Tweet>(x, HttpStatus.OK);
	}

	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<Tweet> replyTweet(@PathVariable("username") String username, @PathVariable("id") int id,
			@RequestBody String message) throws TweetLengthException {
		
		Tweet tweet = tweetService.replyTweet(username, id, message);
		return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() {
		List<Tweet> tweets =  tweetService.allTweets();
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}


}
