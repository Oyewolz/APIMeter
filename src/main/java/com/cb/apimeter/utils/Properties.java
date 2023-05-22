package com.cb.apimeter.utils;

import com.cb.apimeter.enums.UsageBand;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author toyewole
 */

@Getter
@Setter
@Component
@ConfigurationProperties
public class Properties {

    @NotNull(message = "Kindly provide the bandPriceMap")
    @NotEmpty
    private Map<UsageBand, Double> bandPriceMap ;

}
