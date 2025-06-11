package com.sheikh.integratedb.repository;

import com.sheikh.integratedb.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserName(String userName);

    public void deleteByUserName(String userName);
}
