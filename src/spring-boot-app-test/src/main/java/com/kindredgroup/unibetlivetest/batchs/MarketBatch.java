package com.kindredgroup.unibetlivetest.batchs;


import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import com.kindredgroup.unibetlivetest.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class MarketBatch {

    private final MarketService marketService;
    private final BetRepository betRepository;

    @Scheduled(fixedRate = 5000)
    public void payBets() {

        /**long startTime = System.nanoTime();
         log.info("{} bet(s) updated.", marketService.updateWinBet());
         long endTime = System.nanoTime();
         long duration = (endTime - startTime);
         log.info("{} duration update win bet execution", duration);
         **/
        Long startTime = System.nanoTime();
        log.info("{} bet(s) updated", marketService.updateWinBet());
        List<Bet> bets = marketService.updateBet();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        log.info("{} ms duration update win bet execution", duration);

    }

}
