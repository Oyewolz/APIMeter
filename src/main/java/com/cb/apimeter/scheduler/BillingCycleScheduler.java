package com.cb.apimeter.scheduler;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.repository.APILicenseRepository;
import com.cb.apimeter.repository.APIUsageLogRepository;
import com.cb.apimeter.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author toyewole
 */


@Component
@RequiredArgsConstructor
public class BillingCycleScheduler {

    private final APILicenseRepository apiLicenseRepository;

    private final BillingService billingService;

    @Scheduled(cron = "${cron-billing-cycle}")
    public void generateInvoice() {

        boolean canRetreiveData = true;
        int index = 0;
        while (canRetreiveData) {
            List<APILicense> apiLicenseList = apiLicenseRepository.findAllApiLicense(Pageable.ofSize(10).withPage(index));

            billingService.generateInvoice(apiLicenseList);

            canRetreiveData = apiLicenseList.size() == 10;
            index++;
        }
    }


}
