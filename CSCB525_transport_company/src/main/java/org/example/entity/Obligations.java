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
    @OneToOne
    private Employee employee;

    public Obligations(BigDecimal amount, TransportCompany transportCompany, Employee employee) {
        this.amount = amount;
        this.transportCompany = transportCompany;
        this.employee = employee;
    }
    public Obligations(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Obligations{" +
                "id=" + id +
                ", amount=" + amount +
//                ", transportCompany=" + transportCompany +
//                ", employee=" + employee +
                '}';
    }
}
