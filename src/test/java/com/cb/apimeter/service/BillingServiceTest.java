package com.cb.apimeter.service;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.repository.APIUsageLogRepository;
import com.cb.apimeter.repository.InvoiceLogRepository;
import com.cb.apimeter.utils.Properties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author toyewole
 */

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {

    @InjectMocks
    private BillingService billingService;

    @Mock
    private InvoiceLogRepository invoiceLogRepository;

    @Mock
    private APIUsageLogRepository apiUsageLogRepository;

    @Mock
    private Properties properties;

    @Test
    public void givenAListOfConsumers_whenGeneratingInvoice_shouldCreateInvoiceForEach() {

        APILicense apiLicense = new APILicense();
        apiLicense.setConsumerId(UUID.randomUUID().toString());
        APILicense apiLicense2 = new APILicense();
        apiLicense2.setConsumerId(UUID.randomUUID().toString());

        Mockito.when(apiUsageLogRepository.countByConsumerIdAndTimestampBetween(ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(1000L);


        billingService.generateInvoice(List.of(apiLicense, apiLicense2));

        Mockito.verify(invoiceLogRepository, Mockito.times(1))
                .saveAll(ArgumentMatchers.anyList());
        Mockito.verify(apiUsageLogRepository, Mockito.times(2))
                .countByConsumerIdAndTimestampBetween(ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class));


    }

}
