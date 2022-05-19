package com.kindredgroup.unibetlivetest;

import com.kindredgroup.unibetlivetest.entity.Bet;
import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.exception.BusinessException;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.types.SelectionState;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UnibetLiveTestApplicationTests {

    @Test
    void isCurrentOddMatchedTest() {
        Throwable exception = assertThrows(
                BusinessException.class, () -> {
                    Selection selection = new Selection().setCurrentOdd(BigDecimal.valueOf(1.2));
                    selection.isCurrentOddMatched(BigDecimal.valueOf(1));
                }
        );
        assertEquals("odd have changed", exception.getMessage());
    }

    @Test
    void isSelectionClosedTest() {
        Throwable exception = assertThrows(
                BusinessException.class, () -> {
                    Selection selection = new Selection().setState(SelectionState.CLOSED);
                    selection.isSelectionClosed();
                }
        );
        assertEquals("selection is closed", exception.getMessage());
    }


    @Test
    void updateBalanceTest() {
        Throwable exception = assertThrows(
                BusinessException.class, () -> {
                    Customer customer = new Customer().setBalance(BigDecimal.valueOf(50));
                    customer.updateBalance(52L);
                }
        );
        assertEquals("insufficient balance", exception.getMessage());
    }


    @Test
    void isBetExistedTest() {
        Throwable exception = assertThrows(
                CustomException.class, () -> {
                    Customer customer = new Customer().setBets(Collections.singletonList(new Bet().setSelection(new Selection()
                            .setId(1L))));
                    customer.isBetExisted(1L);
                }
        );
        assertEquals("existed bet", exception.getMessage());
    }

}
