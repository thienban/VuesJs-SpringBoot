package com.kindredgroup.unibetlivetest.mapper;

import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class EventMapper {

    public abstract List<EventDto> toEventDtos(List<Event> events);

    @Mapping(target = "selections", source = "markets", qualifiedByName = "selectionsFromEvent")
    @Mapping(target = "bets", source = "markets", qualifiedByName = "betsFromEvent")
    public abstract EventDto toEventDto(Event event);

    @Named("selectionsFromEvent")
    List<Selection> selectionsFromEvent(List<Market> markets) {
        ArrayList<Selection> selections = new ArrayList<>();
        markets.forEach(market -> selections.addAll(market.getSelections()));
        return selections;
    }

    @Named("betsFromEvent")
    List<Bet> betsFromEvent(List<Market> markets) {
        ArrayList<Bet> bets = new ArrayList<>();
        markets.forEach(market -> market.getSelections().forEach(selection -> bets.addAll(selection.getBets())));
        return bets;
    }
}