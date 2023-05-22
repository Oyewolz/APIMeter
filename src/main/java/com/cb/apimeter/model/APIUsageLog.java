package com.cb.apimeter.model;


import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(hidden = true)
    @Id
    private String id;

    @ApiModelProperty(hidden = true)
    private LocalDateTime timestamp = LocalDateTime.now();

    @ApiModelProperty(value = "The uniqueId use to identify a consumer", example = "bb74a33e-467c-4aba-9153-8a20ec812ea0")
    @NotNull(message = "Kindly provide a Consumer ID")
    private String consumerId;

}
