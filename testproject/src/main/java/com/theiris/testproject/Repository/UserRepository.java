package com.theiris.testproject.Repository;

import com.theiris.testproject.Entity.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepository extends MongoRepository<User, Id> {
  Optional<User> findByUsername(String name);
  Optional<User> findByEmail(String email);
  Optional<User> findByPhone(String phone);

}

