package org.example.entity;

import jakarta.validation.constraints.Positive;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id", nullable = false, unique = true)
    private long id;
//    @Column(name = "first_name")
//    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long!")
//    @Pattern(regexp = "^([A-Z].*)", message = "First name should start with a capital letter!")
//    private String firstName;
//    @Column(name = "last_name")
//    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long!")
//    @Pattern(regexp = "^([A-Z].*)", message = "Last name should start with a capital letter!")
//    private String lastName;

    @Column(name = "price_to_pay")
    @Positive
    private BigDecimal priceToPay;

    @Column(name = "paying_status")
    private boolean payingStatus;

    @OneToOne(fetch =  FetchType.LAZY)//, mappedBy = "orderDetails causing .AssertionFailure:
    private TripDetails tripDetails;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "orderDetailsSet", fetch = FetchType.LAZY)
    private Set<Goods> goodsSet;

    public OrderDetails( BigDecimal priceToPay, TripDetails tripDetails) {
//        this.firstName = firstName;
//        this.lastName = lastName;
        this.priceToPay = priceToPay;
        this.tripDetails = tripDetails;
    }

    public OrderDetails(BigDecimal priceToPay, boolean payingStatus, TripDetails tripDetails) {
        this.priceToPay = priceToPay;
        this.payingStatus = payingStatus;
        this.tripDetails = tripDetails;
    }

    public OrderDetails(BigDecimal priceToPay, boolean payingStatus, TripDetails tripDetails, Customer customer) {
        this.priceToPay = priceToPay;
        this.payingStatus = payingStatus;
        this.tripDetails = tripDetails;
        this.customer = customer;
    }

    public OrderDetails(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getPayingStatus() {
        return payingStatus;
    }

    public void setPayingStatus(boolean payingStatus) {
        this.payingStatus = payingStatus;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }

    //    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

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
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
                ", priceToPay=" + priceToPay +
//                ", tripDetails=" + tripDetails +
//                ", customer=" + customer +
                '}';
    }
}
