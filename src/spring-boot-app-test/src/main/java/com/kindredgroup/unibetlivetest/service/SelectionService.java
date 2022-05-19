package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.MarketRepository;
import com.kindredgroup.unibetlivetest.repository.SelectionRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import com.kindredgroup.unibetlivetest.utils.Helpers;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;

@RequiredArgsConstructor
@Component
@Log4j2
public class SelectionService {

    private final SelectionRepository selectionRepository;
    private final MarketRepository marketRepository;

    /**
     * 1. Récupère toute les selections ouvertes
     * 2. Mis à jour la cote aléatoirement
     */

    public Long updateOddsRandomly() {
        final List<Selection> selectionsOpened = selectionRepository.getSelectionByStateEquals(SelectionState.OPENED);
        return selectionsOpened.isEmpty() ? 0 : selectionsOpened
                .stream()
                .map(selection -> selection.setCurrentOdd(Helpers.updateOddRandomly(selection.getCurrentOdd())))
                .map(selectionRepository::save)
                .count();
    }

    /**
     * 1. Récupère toute les selections ouvertes
     * 2. Ferme 5 sélections aléatoirement.
     */

    public Long closeOddsRandomly() {
        final List<Selection> selectionsOpened = selectionRepository.getSelectionByStateEquals(SelectionState.OPENED);
        return selectionsOpened.isEmpty() ? 0 : IntStream
                .range(0, 5)
                .mapToObj(i -> selectionRepository.save(
                        selectionsOpened.get(Helpers.getRandomIndex(0, selectionsOpened.size()))
                                .setState(SelectionState.CLOSED)
                                .setResult(Helpers.setResultRandomly())))
                .count();
    }

    /**
     * Find selection by event identifier
     * @param id identifier
     * @param state selection state {@link SelectionState}
     */
    public List<Selection> findSelectionsByEventId(Long id, Optional<String> state) {
        List<Selection> selections = marketRepository.findSelectionsByEventId(id);
        if (selections.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No existing result");
        }
        if (state.isPresent()) {
            try {
                SelectionState checkedState = SelectionState.valueOf(state.get());
                return selections.stream()
                        .filter(selection -> selection.getState().equals(checkedState))
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Malformed request");
            }
        }
        return selections;
    }

    /**
     * Find selection by identifier
     * @param id identifier
     */
    public Selection findSelectionById(Long id) {
        return selectionRepository.findById(id)
                .orElseThrow(() -> new CustomException(format("%s id is not found", id), ExceptionType.SELECTION_NOT_FOUND));

    }

}
