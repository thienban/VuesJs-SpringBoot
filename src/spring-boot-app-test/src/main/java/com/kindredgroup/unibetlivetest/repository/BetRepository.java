package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {

    Bet findBetBySelectionId(Long id);

    List<Bet> findBetByCustomerPseudo(String pseudo);
}
