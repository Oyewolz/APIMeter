package com.cb.apimeter.model;

import com.cb.apimeter.enums.UsageBand;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author toyewole
 */
@Document
@Data
public class InvoiceLog {

    @Id
    private String id;

    @NotNull
    private String emailAddress;

    @NotNull
    private Double totalAmount;

    @NotNull
    private UsageBand band;

    @NotNull
    private Long apiRequestCount;

}
