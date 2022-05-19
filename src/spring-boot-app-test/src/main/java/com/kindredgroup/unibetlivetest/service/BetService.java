package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.dto.BetDto;
import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
@Log4j2
public class BetService {

    private final BetRepository betRepository;
    private final SelectionService selectionService;
    private final CustomerService customerService;

    /**
     * Check for selection and customer rules by adding bet
     * @param bet bet {@link BetDto}
     */
    public void addBet(BetDto bet) {
        Selection selection = selectionService.findSelectionById(bet.getSelectionId());
        selection.isSelectionClosed();
        selection.isCurrentOddMatched(bet.getSelectionOdd());
        //unibet is connected user for this example, use spring security to get connected user
        Customer customer = customerService.findCustomerByPseudo("unibest");
        customer.isBetExisted(selection.getId());
        customer.updateBalance(bet.getBet());
        betRepository.save(new Bet()
                .setDate(new Date())
                .setCustomer(customer)
                .setSelection(selection)
                .setName(20));
    }

    /**
     * Find bet by customer pseudo
     * @param pseudo customer pseudo
     */
    public List<Bet> getBetByPseudo(String pseudo) {
      return betRepository.findBetByCustomerPseudo(pseudo);
    }

}
