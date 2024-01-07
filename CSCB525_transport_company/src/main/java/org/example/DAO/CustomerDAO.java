package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {
    /**
     * Creates a new client record in the database.
     *
     * @param customer The client object to be created.
     * @throws IllegalArgumentException If the provided client object is null.
     */
    public static void createCustomer(Customer customer){
        if(customer == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        }
    }
    /**
     * Retrieves a list of all customers from the database.
     *
     * @return List of Customer objects representing all customers in the database.
     */
    public static List<Customer> getCustomer(){
        List<Customer> customerList;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            customerList = session.createQuery("select c from Customer c", Customer.class)
                    .getResultList();
            transaction.commit();
        }
        return customerList;
    }
    /**
     * Updates an existing customer record in the database.
     *
     * @param customer The updated customer object.
     * @throws IllegalArgumentException If the provided customer object is null.
     */
    public static void updateCustomer(Customer customer){
        if(customer == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();
        }
    }
    /**
     * Deletes a customer record from the database.
     *
     * @param customer The customer object to be deleted.
     * @throws IllegalArgumentException If the provided customer object is null.
     */
    public static void deleteCustomer(Customer customer){
        if (customer == null){
            throw new IllegalArgumentException("Employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        }
    }

    /**
     * Retrieves a Customer from the database by its ID.
     *
     * @param id The ID of the Customer to be retrieved.
     * @return The Customer object with the given ID, or null if not found.
     */
    public static Customer getCustomerById(long id) {
        Customer customer;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customer = session.get(Customer.class, id);
            transaction.commit();
        }
        return customer;
    }

    /**
     * Updates an existing trip record in the database.
     *
     * @param customer The updated TripDetails object.
     * @throws IllegalArgumentException If the provided trip object is null.
     */
    public static void saveOrUpdateCustomer(Customer customer){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // it used to be saveOrUpdate(), but it's deprecated
            session.merge(customer);
            transaction.commit();
        }
    }
}
