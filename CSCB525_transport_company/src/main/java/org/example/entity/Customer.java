package org.example.entity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long!")
    @Pattern(regexp = "^([A-Z].*)", message = "First name should start with a capital letter!")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long!")
    @Pattern(regexp = "^([A-Z].*)", message = "Last name should start with a capital letter!")
    private String lastName;

    @PositiveOrZero
    @Column(name="balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "customer")
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) //relationship 1:n - Customer:CustomerObligations
//    private Set<CustomerObligation> customerObligation;

    public Customer(String firstName, String lastName, BigDecimal balance, List<OrderDetails> orderDetailsList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.orderDetailsList = orderDetailsList;
    }

    public Customer(String firstName, String lastName, List<OrderDetails> orderDetailsList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderDetailsList = orderDetailsList;
    }
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Customer(){}

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

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                //", orderDetailsList=" + orderDetailsList + //changing to show the properties that aren't connections
                '}';
    }
}
