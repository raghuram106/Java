package com.tweetapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.AuthenticationRequest;


@Repository
public interface AuthRequestRepo extends MongoRepository<AuthenticationRequest, String> {

	
}
 