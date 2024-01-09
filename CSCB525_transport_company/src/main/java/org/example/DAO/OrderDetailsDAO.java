package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.example.entity.OrderDetails;
import org.example.exceptions.InsufficientBalanceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailsDAO {


    /**
     * Creates a new order if the customer has sufficient balance.
     *
     * @param orderDetails The details of the order to be created.
     * @param customer1 The customer who is placing the order.
     * @return The created OrderDetails object, or null if the order could not be created due to insufficient balance.
     * @throws RuntimeException If an InsufficientBalanceException is caught.
     */
    public static OrderDetails createOrder(OrderDetails orderDetails, Customer customer1) {
        OrderDetails order = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            long customerId = customer1.getId();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null && customer.getBalance().compareTo(orderDetails.getPriceToPay()) >= 0) {
                // If the customer exists and has sufficient balance, create the order
                customer1.setBalance((customer1.getBalance()).subtract(orderDetails.getPriceToPay()));
                CustomerDAO.saveOrUpdateCustomer(customer1);
                order = new OrderDetails(orderDetails.getPriceToPay(), orderDetails.getPayingStatus(), orderDetails.getTripDetails(), customer);
                session.persist(order);
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

    /**
     * Saves or updates an order if the customer has sufficient balance.
     * If the customer exists and has sufficient balance, the order is created or updated.
     * The customer's balance is updated, and the order details are set to the customer.
     *
     * @param orderDetails The details of the order to be saved or updated.
     * @param customer1 The customer who is placing the order.
     * @return The saved or updated OrderDetails object.
     * @throws RuntimeException If an InsufficientBalanceException is caught.
     */
    public static OrderDetails saveOrUpdateCreateOrder(OrderDetails orderDetails, Customer customer1) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            long customerId = customer1.getId();
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null && customer.getBalance().compareTo(orderDetails.getPriceToPay()) >= 0) {
                // If the customer exists and has sufficient balance, create the order
                customer1.setBalance((customer1.getBalance()).subtract(orderDetails.getPriceToPay()));
                CustomerDAO.saveOrUpdateCustomer(customer1);
                orderDetails.setCustomer(customer1);
                OrderDetailsDAO.updateOrderDetails(orderDetails);
                session.merge(orderDetails);  // Update the existing orderDetails object
            }
            else{
                throw new InsufficientBalanceException("InsufficientBalanceException: not enough balance");
            }

            transaction.commit();
        } catch (InsufficientBalanceException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;
    }


    /**
     * Updates an existing order record in the database.
     * If the provided OrderDetails object is null, an IllegalArgumentException is thrown.
     *
     * @param orderDetails The updated OrderDetails object.
     * @throws IllegalArgumentException If the provided OrderDetails object is null.
     */
    public static void updateOrderDetails(OrderDetails orderDetails){
        if(orderDetails == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(orderDetails);
            transaction.commit();
        }
    }
}
