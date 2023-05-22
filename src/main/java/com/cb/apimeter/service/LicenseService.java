package com.cb.apimeter.service;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.repository.APILicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author toyewole
 */

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final APILicenseRepository apiLicenseRepository;

    /**
     * @param apiLicense {@link APILicense}
     * @return {@link APILicense} persisted api license
     */
    public APILicense createLicense(APILicense apiLicense) {
        apiLicense.setConsumerId(UUID.randomUUID().toString());
        return apiLicenseRepository.save(apiLicense);
    }
}
