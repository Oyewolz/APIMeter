package com.cb.apimeter.repository;

import com.cb.apimeter.model.InvoiceLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author toyewole
 */
@Repository
public interface InvoiceLogRepository extends MongoRepository<InvoiceLog, String> {
}
