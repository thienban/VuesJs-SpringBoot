package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SelectionRepository extends JpaRepository<Selection, Long> {

    List<Selection> getSelectionByStateEquals(SelectionState state);
}
