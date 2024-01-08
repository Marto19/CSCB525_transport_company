package org.example.configuration;

import org.example.entity.Customer;
import org.example.entity.OrderDetails;
import org.example.entity.TripDetails;

import java.math.BigDecimal;

public class OrderDetailsService {
    /**
     * Creates an OrderDetails object if the customer has sufficient balance.
     *
     * @param priceToPay The price to pay for the order.
     * @param payingStatus The paying status of the order.
     * @param tripDetails The trip details associated with the order.
     * @param customer The customer making the order.
     * @return The created OrderDetails object, or null if the customer's balance is insufficient.
     */
    public OrderDetails createOrderIfBalanceSufficient(BigDecimal priceToPay, boolean payingStatus, TripDetails tripDetails, Customer customer) {
        // Check if the customer's balance is sufficient
        if (customer.getBalance().compareTo(priceToPay) >= 0) {
            // If the balance is sufficient, create the order
            return new OrderDetails(priceToPay, payingStatus, tripDetails, customer);
        } else {
            // If the balance is insufficient, return null
            return null;
        }
    }
}
