package com.cb.apimeter.repository;

import com.cb.apimeter.model.APILicense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author toyewole
 */
@Repository
public interface APILicenseRepository extends MongoRepository<APILicense, String> {

    APILicense getAPILicenseByEmailAddress(String emailAddress);
    @Query("{}")
    List<APILicense> findAllApiLicense(Pageable pageable);

}
