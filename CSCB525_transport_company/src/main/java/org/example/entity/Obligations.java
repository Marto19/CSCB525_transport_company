package org.example.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "obligations")
public class Obligations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private long id;
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "transport_company_id")
    private TransportCompany transportCompany;
    //private Employee employee;
}
