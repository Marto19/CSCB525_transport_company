package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.example.entity.OrderDetails;
import org.example.exceptions.InsufficientBalanceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailsDAO {


    /**
     *
     * @param orderDetails
     * @param customer1
     * @return
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
    //TODO: make many to many qualification type and vehicle type and make check in the dao when creating a trip or smh

    /**
     *
     * @param orderDetails
     * @param customer1
     * @return
     */
    public static OrderDetails saveOrUpdateCreateOrder(OrderDetails orderDetails, Customer customer1) {
        OrderDetails order = null;
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
                order = new OrderDetails(orderDetails.getPriceToPay(), orderDetails.getPayingStatus(), orderDetails.getTripDetails(), customer);
                session.merge(order);
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
     * Updates an existing customer record in the database.
     *
     * @param orderDetails The updated customer object.
     * @throws IllegalArgumentException If the provided customer object is null.
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
