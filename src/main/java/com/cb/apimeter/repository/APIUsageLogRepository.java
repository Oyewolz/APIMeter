package com.cb.apimeter.repository;

import com.cb.apimeter.model.APIUsageLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

/**
 * @author toyewole
 */
public interface APIUsageLogRepository extends MongoRepository<APIUsageLog, String> {
   Long countByConsumerIdAndTimestampBetween(String licenseKey, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
   APIUsageLog getAPIUsageLogByConsumerId(String consumerId);
}
