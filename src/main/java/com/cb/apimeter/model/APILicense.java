package com.cb.apimeter.model;

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

    @Id
    private String id;

    @Indexed(unique = true)
    private String consumerId;

    @NotNull(message = "Kindly Provide an email")
    @Email(message = "Kindly provide consumer email address")
    @Indexed(unique = true)
    private String emailAddress;

    private LocalDateTime createTimestamp = LocalDateTime.now();

}
