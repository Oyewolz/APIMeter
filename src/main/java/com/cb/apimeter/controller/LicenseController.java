package com.cb.apimeter.controller;

import com.cb.apimeter.model.APILicense;
import com.cb.apimeter.service.LicenseService;
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
@RequestMapping("license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @ApiOperation(value = "Create Consumer License")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created consumer License"),
            @ApiResponse(code = 400, message = "Bad Request either email is not sent or duplicate email "),

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public APILicense licenseUser(@RequestBody @Valid APILicense apiLicense){
        return licenseService.createLicense(apiLicense);
    }


}
