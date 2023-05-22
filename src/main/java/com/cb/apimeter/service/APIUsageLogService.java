package com.cb.apimeter.service;

import com.cb.apimeter.model.APIUsageLog;
import com.cb.apimeter.repository.APIUsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author toyewole
 */

@Service
@RequiredArgsConstructor
public class APIUsageLogService {

    private final APIUsageLogRepository apiUsageLogRepository;

    /**
     * @param  apiUsageLog {@link APIUsageLog}
     * @return {@link APIUsageLog} the persisted APIUsageLog
     * */
    public APIUsageLog logUsage(APIUsageLog apiUsageLog) {
        return apiUsageLogRepository.save(apiUsageLog);
    }

}
