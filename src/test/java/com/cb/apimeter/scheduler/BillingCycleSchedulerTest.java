package com.cb.apimeter.scheduler;

/**
 * @author toyewole
 */


import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.model.InvoiceLog;
import com.cb.apimeter.repository.APILicenseRepository;
import com.cb.apimeter.repository.APIUsageLogRepository;
import com.cb.apimeter.repository.InvoiceLogRepository;
import org.awaitility.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "cron-billing-cycle= */1 * * * * *"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BillingCycleSchedulerTest {

    @Autowired
    private APILicenseRepository apiLicenseRepository;

    @SpyBean
    private APIUsageLogRepository apiUsageLogRepository;


    @Autowired
    @SpyBean
    private InvoiceLogRepository invoiceLogRepository;

    private final String email = "test@test.com";

    @BeforeAll
    void setUp() {
        APILicense apiLicense = new APILicense();
        apiLicense.setEmailAddress(email);
        apiLicense.setConsumerId(UUID.randomUUID().toString());

        apiLicenseRepository.save(apiLicense);
    }

    /**
     * increase the time in debug mode otherwise it will fail
     */
    @Test
    public void whenWaitOneSecond_thenScheduledIsCalledAtLeastTenTimes() throws InterruptedException {

        Mockito.when(apiUsageLogRepository.countByConsumerIdAndTimestampBetween(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(LocalDateTime.class),
                ArgumentMatchers.any(LocalDateTime.class))).thenReturn(1000L);

        //scheduler set to trigger in few seconds
        await().atMost(Duration.ONE_SECOND)
                .untilAsserted(() ->
                        Mockito.verify(invoiceLogRepository, Mockito.atLeast(1))
                                .saveAll(ArgumentMatchers.anyList()));
        //so that db changes can be cascaded
        Thread.sleep(1000L);

        List<InvoiceLog> invoiceLogs = invoiceLogRepository.findAll();

        //expecting an invoice since it is only one consumer on the db
        Assertions.assertFalse(invoiceLogs.isEmpty());
        Assertions.assertEquals(email, invoiceLogs.get(0).getEmailAddress());


    }
}
