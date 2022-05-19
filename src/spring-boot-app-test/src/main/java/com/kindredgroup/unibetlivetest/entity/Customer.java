package com.kindredgroup.unibetlivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindredgroup.unibetlivetest.exception.BusinessException;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.types.BusinessExceptionType;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Table(name = "customer")
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Customer {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "balance")
    private BigDecimal balance;

    @JsonIgnore
    @OneToMany(targetEntity = Bet.class, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Bet> bets = new ArrayList<>();

    public void updateBalance(Long bet) {
        if (balance.subtract(BigDecimal.valueOf(bet)).compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(BigDecimal.valueOf(bet));
        } else {
            throw new BusinessException("insufficient balance", BusinessExceptionType.INSUFFICIENT_BALANCE);
        }

    }

    public void isBetExisted(Long selectionId) {
        if (bets.stream().anyMatch(customerBet -> customerBet.getSelection().getId().equals(selectionId))) {
            throw new CustomException("existed bet", ExceptionType.EXISTED_BET);
        }
    }

}
