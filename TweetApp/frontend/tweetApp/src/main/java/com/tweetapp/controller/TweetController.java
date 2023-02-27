package com.tweetapp.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tweetapp.exception.InvalidUserException;
import com.tweetapp.exception.TweetLengthException;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TweetController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TweetController.class);

	@Autowired
	TweetService tweetService;
	
	@GetMapping("/all/{id}")
	public ResponseEntity<Tweet> getTweet(@PathVariable("id") int id) {
		log.info("BEGIN - [ Retrieve Tweet]");
		Tweet t = tweetService.getTweet(id);
		log.info("End - [Retrieve Tweet]");
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

	@PostMapping("/{username}/add")
	public ResponseEntity<Void> postTweet(@PathVariable("username") String username, @RequestBody Tweet tweet) throws TweetLengthException, InvalidUserException {
		log.info("BEGIN - [Post Tweet]");
		Tweet t = tweetService.postTweet(tweet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getLoginId()).toUri();
		log.info("END - [Post Tweet]");
		return ResponseEntity.created(uri).build();

	}

	@GetMapping("/user/search/{username}")
	public ResponseEntity<List<Tweet>> searchTweet(@PathVariable("username") String username) {
		log.info("BEGIN - [Search Tweet]");
		List<Tweet> tweets = tweetService.searchTweet(username);
		log.info("END - [Search Tweet]");
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}

	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet tweet) throws InvalidUserException {
		log.info("BEGIN - [Update Tweet]");
		Tweet t = tweetService.updateTweet(tweet);
		log.info("END - [Update Tweet]");
		return new ResponseEntity<Tweet>(t, HttpStatus.OK);
	}

	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<Void> deleteTweet(@PathVariable("username") String username, @PathVariable("id") int id) throws InvalidUserException {
		log.info("BEGIN - [Delete Tweet]");
		Tweet t = tweetService.deleteTweet(username,id);
		if (t != null) {
			return ResponseEntity.noContent().build();
		}
		log.info("END- [Delete Tweet]");
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<Tweet> likeTweet(@PathVariable("username") String username, @PathVariable("id") int id) {
		log.info("BEGIN - [Like Tweet]");
		Tweet x = tweetService.likeTweet(username, id);
		log.info("End - [Like Tweet]");
		return new ResponseEntity<Tweet>(x, HttpStatus.OK);
	}

	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<Tweet> replyTweet(@PathVariable("username") String username, @PathVariable("id") int id,
			@RequestBody String message) throws TweetLengthException {
		log.info("BEGIN - [Reply Tweet]");
		Tweet tweet = tweetService.replyTweet(username, id, message);
		log.info("END - [Reply Tweet]");
		return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);

	}


	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() {
		log.info("BEGIN - [All Tweets]");
		log.info("END - [All Tweets]");
		List<Tweet> tweets =  tweetService.allTweets();
		return new ResponseEntity<>(tweets, HttpStatus.OK);
	}


}
