package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweetapp.model.User;

public interface UserRepo extends MongoRepository<User, String> {

	@Query("{'loginId':{'$regex':'?0','$options':'i'}}")  
	List<User> searchByRegex(String str);

}
