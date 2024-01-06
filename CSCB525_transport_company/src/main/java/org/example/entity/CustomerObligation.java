package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_obligations")
public class CustomerObligation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @Column(name = "paying_status", nullable = false)
    private boolean paid = false;

    @ManyToOne(fetch = FetchType.LAZY)//relationship n:1 - customerObligations:customer
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY) //relationship 1:1 - customerObligations:trip
    private TripDetails tripDetails;

    //TODO: test the above two connections in Main, by making records

    public CustomerObligation(boolean paid, Customer customer) {
        this.paid = paid;
        this.customer = customer;
    }

    public CustomerObligation() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TripDetails getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(TripDetails tripDetails) {
        this.tripDetails = tripDetails;
    }

    @Override
    public String toString() {
        return "CustomerObligation{" +
                "id=" + id +
                ", paid=" + paid +
                ", customer=" + customer +
                '}';
    }

}
