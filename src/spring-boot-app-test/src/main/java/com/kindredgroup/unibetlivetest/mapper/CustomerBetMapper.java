package com.kindredgroup.unibetlivetest.mapper;

import com.kindredgroup.unibetlivetest.dto.CustomerBetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public abstract class CustomerBetMapper {

    public abstract List<CustomerBetDto> customerBetDto(List<Bet> bets);

    @Mapping(target = "currentOdd", source = "selection", qualifiedByName = "oddFromSelection")
    @Mapping(target = "event", source = "selection", qualifiedByName = "eventFromSelection")
    @Mapping(target = "market", source = "selection", qualifiedByName = "marketFromSelection")
    @Mapping(target = "selectionName", source = "selection", qualifiedByName = "selectionNameFromSelection")
    public abstract CustomerBetDto customerBetDto(Bet bet);

    @Named("oddFromSelection")
    BigDecimal oddFromSelection(Selection selection) {
        return selection.getCurrentOdd();
    }

    @Named("eventFromSelection")
    String eventFromSelection(Selection selection) {
        return selection.getMarket().getEvent().getName();
    }

    @Named("marketFromSelection")
    String marketFromSelection(Selection selection) {
        return selection.getMarket().getName();
    }

    @Named("selectionNameFromSelection")
    String selectionNameFromSelection(Selection selection) {
        return selection.getName();
    }

}