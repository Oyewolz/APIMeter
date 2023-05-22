package com.cb.apimeter.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author toyewole
 */

@Document(collection = "apiLicense")
@Data
public class APILicense {

    @ApiModelProperty(hidden = true)
    @Id
    private String id;

    @ApiModelProperty(hidden = true)
    @Indexed(unique = true)
    private String consumerId;

    @ApiModelProperty(value = "Consumer email address ", example = "test@mail.com")
    @NotNull(message = "Kindly Provide an email")
    @Email(message = "Kindly provide consumer email address")
    @Indexed(unique = true)
    private String emailAddress;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createTimestamp = LocalDateTime.now();

}
