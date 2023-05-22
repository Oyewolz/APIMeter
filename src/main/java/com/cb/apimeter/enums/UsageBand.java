package com.cb.apimeter.enums;

import lombok.Getter;

/**
 * @author toyewole
 */

@Getter
public enum UsageBand {

    STARTER(1000000L, 5.0d),
    PREMIUM(10000000L, 4.12d),
    PLATINUM(Long.MAX_VALUE, 3.5d);


    private final Long limit;
    private final Double defaultPrice;

    UsageBand(Long limit, Double defaultPrice) {
        this.limit = limit;
        this.defaultPrice = defaultPrice;
    }

}
