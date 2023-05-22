package com.cb.apimeter.controller;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.service.APIUsageLogService;
import com.cb.apimeter.service.LicenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author toyewole
 */

@WebMvcTest
public class LicenseControllerTest {

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

        var apiLicense = new APILicense();
        apiLicense.setEmailAddress("test@yopmail.com");

        Mockito.when(licenseService.createLicense(ArgumentMatchers.any(APILicense.class)))
                .thenAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        MvcResult result = mockMvc.perform(post("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apiLicense)))
                .andExpect(status().isCreated())
                .andReturn();

        APILicense response = objectMapper.readValue(result.getResponse().getContentAsString(), APILicense.class);


        Assertions.assertNotNull(response);
        Assertions.assertEquals(apiLicense.getConsumerId(), response.getConsumerId());
    }

    @ParameterizedTest
    @CsvSource({
            ",Kindly Provide an email",
            "taiwo,Kindly provide consumer email address"
    })
    public void givenInvalidUsageLogObj_whenLoggingUsageLog_thenReturnBadRequest(String email, String errorResp) throws Exception {

        var apiLicense = new APILicense();
        apiLicense.setEmailAddress(email);

        Mockito.when(licenseService.createLicense(ArgumentMatchers.any(APILicense.class)))
                .thenAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        MvcResult result = mockMvc.perform(post("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(apiLicense)))
                .andExpect(status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(errorResp, result.getResponse().getContentAsString());
    }

}
