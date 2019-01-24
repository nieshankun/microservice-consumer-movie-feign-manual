package com.nsk.cloud.microservicesimpleconsumermovie.user;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nsk
 * 2018/6/26 20:27
 */
@Data
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
