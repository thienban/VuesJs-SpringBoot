package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.BetState;
import com.kindredgroup.unibetlivetest.types.SelectionResult;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Log4j2
public class MarketService {

    private final BetRepository betRepository;

    /**
     * Update bet to win {@link BetState} with selection {@link Selection } condition
     */
    public long updateWinBet() {
        return betRepository.findAll().stream()
                .filter(bet -> bet.getSelection().getResult() != null && bet.getSelection()
                        .getResult()
                        .equals(SelectionResult.WON) && bet.getSelection()
                        .getState()
                        .equals(SelectionState.CLOSED))
                .map(bet -> bet.setBetState(BetState.WON))
                .map(betRepository::save)
                .count();
    }

    public List<Bet> updateBet() {
        return betRepository.findAll().stream()
                .map(bet -> bet.setBetState(BetState.WON))
                .map(betRepository::save).collect(Collectors.toList());
    }

}
