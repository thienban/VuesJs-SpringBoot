package com.kindredgroup.unibetlivetest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
@Data
public class BetDto implements Serializable {

    @ApiModelProperty(required = true, example = "3")
    private Long selectionId;

    @ApiModelProperty(required = true, example = "1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal selectionOdd;

    @ApiModelProperty(required = true, example = "2")
    private Long bet;

}
