package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.model.Tweet;

public interface TweetRepo extends MongoRepository<Tweet, String> {

}
