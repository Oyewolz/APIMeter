package com.cb.apimeter.integrationtest;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.model.APIUsageLog;
import com.cb.apimeter.repository.APILicenseRepository;
import com.cb.apimeter.repository.APIUsageLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author toyewole
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApiUsageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private APILicenseRepository apiLicenseRepository;
    @Autowired
    private APIUsageLogRepository apiUsageLogRepository;
    @Test
    public void createAPILicense_shouldPersistDataOnDb() throws Exception {

        APILicense apiLicense = new APILicense();
        apiLicense.setEmailAddress("test@gmail.com");

        mockMvc.perform(post("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apiLicense)))
                .andExpect(status().isCreated())
                .andReturn();

        APILicense apiLicenseDb = apiLicenseRepository.getAPILicenseByEmailAddress(apiLicense.getEmailAddress());

        Assertions.assertNotNull(apiLicenseDb);
        Assertions.assertNotNull(apiLicenseDb.getConsumerId());
        Assertions.assertNotNull(apiLicenseDb.getId());

    }


    @Test
    public void createApiUsage_shouldPersistDataOnDb() throws Exception {

        APIUsageLog apiUsageLog = new APIUsageLog();
        apiUsageLog.setConsumerId(UUID.randomUUID().toString());

        mockMvc.perform(post("/usage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apiUsageLog)))
                .andExpect(status().isCreated())
                .andReturn();

        APIUsageLog apiUsageLogDb = apiUsageLogRepository.getAPIUsageLogByConsumerId(apiUsageLog.getConsumerId());

        Assertions.assertNotNull(apiUsageLogDb);
        Assertions.assertNotNull(apiUsageLogDb.getId());

    }
}
