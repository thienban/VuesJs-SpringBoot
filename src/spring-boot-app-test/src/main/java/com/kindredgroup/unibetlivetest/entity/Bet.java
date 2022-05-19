package com.kindredgroup.unibetlivetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindredgroup.unibetlivetest.types.BetState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "bet")
@Entity
@Data
@Accessors(chain = true)
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private int name;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "selection_id", nullable = false)
    @JsonIgnore
    private Selection selection;

    @Column(name = "state")
    private BetState betState;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

}
