package com.kindredgroup.unibetlivetest.api;

import com.kindredgroup.unibetlivetest.dto.EventDto;
import com.kindredgroup.unibetlivetest.dto.SelectionDto;
import com.kindredgroup.unibetlivetest.mapper.EventMapper;
import com.kindredgroup.unibetlivetest.mapper.SelectionMapper;
import com.kindredgroup.unibetlivetest.service.EventService;
import com.kindredgroup.unibetlivetest.service.SelectionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping(Urls.basePath)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventApi {

    private final EventService eventService;
    private final SelectionService selectionService;
    private final EventMapper eventMapper;
    private final SelectionMapper selectionMapper;


    /**
     * Get live events.
     *
     * @return live events
     */
    @ApiOperation("Get live events")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "There is no existing event", response = String.class)
    })
    @GetMapping(Urls.events)
    public List<EventDto> getEvents(@RequestParam Optional<Boolean> isLive) {
        return eventMapper.toEventDtos(eventService.findEvents(isLive));
    }

    /**
     * Get selections by event identifier.
     *
     * @return selections by event identifier
     * <≥Ω></≥Ω>
     */
    @ApiOperation("Get selections by event identifier")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "There is no existing selection", response = String.class),
            @ApiResponse(code = 400, message = "Malformed request", response = String.class)
    })
    @GetMapping(Urls.selections)
    public List<SelectionDto> getSelectionsByEvent(@PathVariable Long id, @RequestParam Optional<String> state) {
        return selectionMapper.toSelectionDtos(selectionService.findSelectionsByEventId(id, state));
    }

}
