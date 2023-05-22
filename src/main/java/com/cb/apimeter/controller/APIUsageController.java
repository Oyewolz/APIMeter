package com.cb.apimeter.controller;

import com.cb.apimeter.model.APIUsageLog;
import com.cb.apimeter.service.APIUsageLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author toyewole
 */

@RestController
@RequestMapping("usage")
@RequiredArgsConstructor
public class APIUsageController {

    private final APIUsageLogService apiUsageLogService ;

    @ApiOperation(value = "Log API Usage")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully log request"),
            @ApiResponse(code = 400, message = "Bad Request"),

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public APIUsageLog logUsage(@RequestBody @Valid APIUsageLog apiUsageLog) {
       return apiUsageLogService.logUsage(apiUsageLog);
    }

}
