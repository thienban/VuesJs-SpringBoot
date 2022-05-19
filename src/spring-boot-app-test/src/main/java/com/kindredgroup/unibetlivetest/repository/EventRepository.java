package com.kindredgroup.unibetlivetest.repository;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

}
