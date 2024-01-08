package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.example.entity.OrderDetails;
import org.example.exceptions.InsufficientBalanceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailsDAO {
    public static OrderDetails createOrder(OrderDetails orderDetails, long customerId) {
        OrderDetails order = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, customerId);
            if (customer != null && customer.getBalance().compareTo(orderDetails.getPriceToPay()) >= 0) {
                // If the customer exists and has sufficient balance, create the order
                order = new OrderDetails(orderDetails.getPriceToPay(), orderDetails.getPayingStatus(), orderDetails.getTripDetails(), customer);
                session.save(order);
            }
            else{
                throw new InsufficientBalanceException("InsufficientBalanceException: not enough balance");

            }

            transaction.commit();
        } catch (InsufficientBalanceException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
