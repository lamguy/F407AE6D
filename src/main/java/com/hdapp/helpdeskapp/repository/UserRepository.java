package com.hdapp.helpdeskapp.repository;

import com.hdapp.helpdeskapp.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
