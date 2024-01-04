package org.example.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "price_to_pay")
    private BigDecimal priceToPay;
    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private TripDetails tripDetails;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OrderDetails(long id, String firstName, String lastName, BigDecimal priceToPay, TripDetails tripDetails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.priceToPay = priceToPay;
        this.tripDetails = tripDetails;
    }

    public OrderDetails(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getPriceToPay() {
        return priceToPay;
    }

    public void setPriceToPay(BigDecimal priceToPay) {
        this.priceToPay = priceToPay;
    }

    public TripDetails getTrip() {
        return tripDetails;
    }

    public void setTrip(TripDetails tripDetails) {
        this.tripDetails = tripDetails;
    }

    public TripDetails getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(TripDetails tripDetails) {
        this.tripDetails = tripDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", priceToPay=" + priceToPay +
//                ", tripDetails=" + tripDetails +
//                ", customer=" + customer +
                '}';
    }
}
