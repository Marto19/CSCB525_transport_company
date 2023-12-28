package org.example.DAO;

import com.mysql.cj.xdevapi.Client;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {
    /**
     * Creates a new client record in the database.
     *
     * @param client The client object to be created.
     * @throws IllegalArgumentException If the provided client object is null.
     */
    public static void createClient(Client client){
        if(client == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(client);
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
            session.saveOrUpdate(customer);
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


}