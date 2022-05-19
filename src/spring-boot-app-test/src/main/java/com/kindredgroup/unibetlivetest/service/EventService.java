package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    /**
     * Find event
     * @param isLive true if event is in live
     * @return
     */
    public List<Event> findEvents(Optional<Boolean> isLive) {
        List<Event> events = eventRepository.findAll();
        if (isLive.isPresent() && Boolean.TRUE.equals(isLive.get())) {
            //use now to check if there is live
            return events.stream()
                    .filter(event -> event.getStartDate().equals(new Date()))
                    .collect(Collectors.toList());
        }
        if (events.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No existing event");
        }
        return events;
    }



}
