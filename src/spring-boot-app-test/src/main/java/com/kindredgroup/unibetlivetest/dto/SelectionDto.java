package com.kindredgroup.unibetlivetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SelectionDto {
    private Long id;
    private String name;
    private BigDecimal currentOdd;
    private String state;
    private String result;
    private String marketName;
    private Long marketId;

}