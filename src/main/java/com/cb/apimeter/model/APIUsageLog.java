package com.cb.apimeter.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author toyewole
 */

@Document
@Data
public class APIUsageLog {

    @Id
    private String id;

    private LocalDateTime timestamp = LocalDateTime.now();

    @NotNull(message = "Kindly provide a Consumer ID")
    private String consumerId;

}
