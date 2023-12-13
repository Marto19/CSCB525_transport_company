package org.example.entity;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "obligations")
public class Obligations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "amount")
    private BigDecimal amount;
    //@ManyToOne
   // @Column(name = "transport_company_id")
    //private TransportCompany transportCompany;
    //private Employee employee;
}
