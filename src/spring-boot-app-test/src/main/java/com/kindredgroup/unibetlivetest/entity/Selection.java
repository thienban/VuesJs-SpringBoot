package com.kindredgroup.unibetlivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindredgroup.unibetlivetest.exception.BusinessException;
import com.kindredgroup.unibetlivetest.types.BusinessExceptionType;
import com.kindredgroup.unibetlivetest.types.SelectionResult;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "selection")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "current_odd")
    BigDecimal currentOdd;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    SelectionState state;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    SelectionResult result;

    @ManyToOne
    @JoinColumn(name = "market_id")
    @JsonIgnore
    Market market;

    @OneToMany(targetEntity=Bet.class, mappedBy="selection", fetch = FetchType.LAZY)
    private List<Bet> bets = new ArrayList<>();

    public ResponseEntity<String> isCurrentOddMatched(BigDecimal odd) {
        if (odd.compareTo(currentOdd) != 0) {
            throw new BusinessException("odd have changed", BusinessExceptionType.ODD_HAVE_CHANGED);
        }
        return null;
    }

    public void isSelectionClosed() {
        if (state.equals(SelectionState.CLOSED)) {
            throw new BusinessException("selection is closed", BusinessExceptionType.CLOSED_SELECTION);
        }
    }

}
