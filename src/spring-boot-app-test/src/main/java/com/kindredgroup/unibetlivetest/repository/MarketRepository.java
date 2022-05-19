package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Market;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Integer> {

    @Query("SELECT m.selections from Market m where m.event.id = :id")
    List<Selection> findSelectionsByEventId(Long id);

}
