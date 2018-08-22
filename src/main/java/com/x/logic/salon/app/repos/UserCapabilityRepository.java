package com.x.logic.salon.app.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.x.logic.salon.app.data.modal.UserCapability;


@Repository
public interface UserCapabilityRepository extends MongoRepository<UserCapability, String> {
}
