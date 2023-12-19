package org.example.DTO;

import org.example.entity.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private String firstName;
    private String lastName;
    private List<OrderDetails> orderDetailsList= new ArrayList<>();

    public CustomerDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
