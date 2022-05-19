package com.kindredgroup.unibetlivetest.mapper;

import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public abstract class SelectionMapper {

    public abstract List<SelectionDto> toSelectionDtos(List<Selection> selections);

    @Mapping(target = "marketName", source = "market", qualifiedByName = "marketNameFromMarket")
    @Mapping(target = "marketId", source = "market", qualifiedByName = "marketIdFromMarket")
    public abstract SelectionDto toSelectionDto(Selection selection);

    @Named("marketNameFromMarket")
    String marketNameFromMarket(Market market) {
        return market.getName();
    }

    @Named("marketIdFromMarket")
    Long marketIdFromMarket(Market market) {
        return market.getId();
    }

}