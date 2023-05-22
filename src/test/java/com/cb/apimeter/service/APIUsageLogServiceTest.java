package com.cb.apimeter.service;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.model.APIUsageLog;
import com.cb.apimeter.repository.APILicenseRepository;
import com.cb.apimeter.repository.APIUsageLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author toyewole
 */

@ExtendWith(MockitoExtension.class)
public class APIUsageLogServiceTest {

    @InjectMocks
    private APIUsageLogService apiUsageLogService;

    @Mock
    private APIUsageLogRepository apiUsageLogRepository;

    @Test
    public void givenAPILicense_whenCreateLicense_shouldCreateLicense() {

        apiUsageLogService.logUsage(new APIUsageLog());

        Mockito.verify(apiUsageLogRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(APIUsageLog.class));

    }


}
