package com.cb.apimeter.repository;

import com.cb.apimeter.model.APIUsageLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;

/**
 * @author toyewole
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ApiUsageRepositoryTest {

    @Autowired
    private APIUsageLogRepository apiUsageLogRepository;

    @Test
    void whenUsageLogIsPersisted_shouldReturnCorrectCount() {
        String licenseKey = UUID.randomUUID().toString();
        APIUsageLog apiUsageLog = new APIUsageLog();
        apiUsageLog.setConsumerId(licenseKey);

        APIUsageLog apiUsageLog2 = new APIUsageLog();
        apiUsageLog2.setConsumerId(licenseKey);


        apiUsageLogRepository.save(apiUsageLog);
        apiUsageLogRepository.save(apiUsageLog2);

        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        LocalDateTime endOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
        Long count = apiUsageLogRepository.countByConsumerIdAndTimestampBetween(licenseKey, startOfMonth, endOfMonth);

        Assertions.assertEquals(2, count);

    }

}
