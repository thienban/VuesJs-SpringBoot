package com.kindredgroup.unibetlivetest.dto;

import com.kindredgroup.unibetlivetest.types.BetState;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@Data
public class CustomerBetDto implements Serializable {

    private Long id;
    private int name;
    private Date date;
    private String betState;

    private BigDecimal currentOdd;
    private String event;
    private String market;
    private String selectionName;

}
