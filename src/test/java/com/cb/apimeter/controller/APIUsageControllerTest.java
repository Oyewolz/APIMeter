package com.cb.apimeter.controller;

import com.cb.apimeter.model.APIUsageLog;
import com.cb.apimeter.service.APIUsageLogService;
import com.cb.apimeter.service.LicenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author toyewole
 */

@WebMvcTest
public class APIUsageControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private APIUsageLogService apiUsageLogService;

    @MockBean
    private LicenseService licenseService;
    @Test
    public void givenUsageLogObj_whenLoggingUsageLog_thenReturnSavedUsageLog() throws Exception {

        var apiUsageLog = new APIUsageLog();
        apiUsageLog.setConsumerId(UUID.randomUUID().toString());

        Mockito.when(apiUsageLogService.logUsage(ArgumentMatchers.any(APIUsageLog.class)))
                .thenAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        MvcResult result = mockMvc.perform(post("/usage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apiUsageLog)))
                .andExpect(status().isCreated())
                .andReturn();

        APIUsageLog response = objectMapper.readValue(result.getResponse().getContentAsString(), APIUsageLog.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(apiUsageLog.getConsumerId(), response.getConsumerId());
    }

    @Test
    public void givenInvalidAPIUsageObj_whenLoggingUsageLog_thenReturnBadRequest() throws Exception {

        Mockito.when(apiUsageLogService.logUsage(ArgumentMatchers.any(APIUsageLog.class)))
                .thenAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        MvcResult result =  mockMvc.perform(post("/usage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( new APIUsageLog())))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertEquals("Kindly provide a Consumer ID", result.getResponse().getContentAsString());

    }

}
