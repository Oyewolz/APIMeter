package com.cb.apimeter.service;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.repository.APILicenseRepository;
import com.cb.apimeter.repository.InvoiceLogRepository;
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
public class LicenseServiceTest {

    @InjectMocks
    private LicenseService licenseService;

    @Mock
    private APILicenseRepository apiLicenseRepository;

    @Test
    public void givenAPILicense_whenCreateLicense_shouldCreateLicense() {

        licenseService.createLicense(new APILicense());

        Mockito.verify(apiLicenseRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(APILicense.class));

    }


}
