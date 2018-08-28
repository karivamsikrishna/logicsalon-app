package com.x.logic.salon.app.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.x.logic.salon.app.data.modal.CompanyDetails;
import com.x.logic.salon.app.data.modal.Store;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {

}
