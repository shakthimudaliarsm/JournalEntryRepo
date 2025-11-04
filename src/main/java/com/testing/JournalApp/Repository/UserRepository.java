package com.testing.JournalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.testing.JournalApp.Entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{

	User findByUsername(String username);
	void deleteByUsername(String username);
}
