package com.sheikh.integratedb.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<com.sheikh.integratedb.models.JournalEntry, String> {
}
