package com.cb.apimeter.service;

import com.cb.apimeter.enums.UsageBand;
import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.model.InvoiceLog;
import com.cb.apimeter.repository.APIUsageLogRepository;
import com.cb.apimeter.repository.InvoiceLogRepository;
import com.cb.apimeter.utils.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author toyewole
 */


@Service
@RequiredArgsConstructor
public class BillingService {

    private final APIUsageLogRepository apiUsageLogRepository;
    private final InvoiceLogRepository invoiceLogRepository;
    private final Properties properties;

    /**
     * @param licenses {@link List<APILicense>} list of register consumer
     *   got through the list and generate invoice based on the API Usage log
     * */
    public void generateInvoice(List<APILicense> licenses) {

        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        LocalDateTime endOfMonth = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);

        List<InvoiceLog> invoiceLogs = licenses.stream().map(license -> {

            long usageCount = apiUsageLogRepository.countByConsumerIdAndTimestampBetween(license.getConsumerId(), startOfMonth, endOfMonth);
            double totalPrice = 0d;
            UsageBand band = UsageBand.STARTER;
            if (usageCount > 0) {

                band = getUsageBand(usageCount);
                Double pricePer1k = properties.getBandPriceMap().get(band);
                pricePer1k = pricePer1k == null ? band.getDefaultPrice() : pricePer1k;

                totalPrice = usageCount * pricePer1k / 1000;
            }
            return getInvoiceLog(totalPrice, band, license.getEmailAddress());

        }).collect(Collectors.toList());

        invoiceLogRepository.saveAll(invoiceLogs);
    }

    /**
     * @param totalPrice {@link Double}
     * @param usageBand {@link UsageBand}
     * @param emailAddress {@link  String}
     * @return  InvoiceLong @{@link InvoiceLog}
     * */
    private InvoiceLog getInvoiceLog(Double totalPrice, UsageBand usageBand, String emailAddress) {
        var invoicelog = new InvoiceLog();
        invoicelog.setBand(usageBand);
        invoicelog.setTotalAmount(totalPrice);
        invoicelog.setEmailAddress(emailAddress);
        return invoicelog;
    }


    /**
     * @param usageCount {@link Long}
     * @return  {@link UsageBand} api usageband base on the usageCount
     * **/
    private UsageBand getUsageBand(final long usageCount) {

        if (usageCount <= UsageBand.STARTER.getLimit()) {
            return UsageBand.STARTER;
        }
        if (usageCount <= UsageBand.PREMIUM.getLimit()) {
            return UsageBand.STARTER;
        }
        return UsageBand.PLATINUM;
    }


}
