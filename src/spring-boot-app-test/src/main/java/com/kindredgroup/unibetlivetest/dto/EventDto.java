package com.kindredgroup.unibetlivetest.dto;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class EventDto {
    private long id;
    private String name;
    private Date startDate;
    private List<MarketDto> markets;
    private List<Selection> selections;
    private List<Bet> bets;

}