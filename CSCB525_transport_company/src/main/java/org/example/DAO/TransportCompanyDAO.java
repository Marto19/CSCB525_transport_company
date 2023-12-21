package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TransportCompany;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransportCompanyDAO {


    /**
     * function that creates new company in the database - CREATE
     * @param transportCompany
     * @throws IllegalArgumentException if provided company is null
     */
    public static void createCompany(TransportCompany transportCompany){
        if(transportCompany == null){
            throw new IllegalArgumentException("The transport company cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(transportCompany);
            transaction.commit();
        }
    }

    /**
     * function that lists every company -READ
     * @param - null
     *  //add exception
     * @return
     */
    public static List<TransportCompany> getCompanies(){
        List<TransportCompany> transportCompanyList;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            transportCompanyList = session.createQuery("select c from TransportCompany c", TransportCompany.class)
                    .getResultList();
            transaction.commit();
        }
        return transportCompanyList;
    }

    /**
     * function to update and save state of the company - UPDATE
     * @param transportCompany
     * @throws IllegalArgumentException if the given company == null
     */
    public static void updateCompany(TransportCompany transportCompany){
        if(transportCompany == null){
            throw new IllegalArgumentException("The transport company cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(transportCompany);
            transaction.commit();
        }
    }

    /**
     * function to delete company - DELETE
     * @param transportCompany
     * @throws IllegalArgumentException if the given company == null
     */
    public static void deleteCompany(TransportCompany transportCompany){
        if (transportCompany == null){
            throw new IllegalArgumentException("Company cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(transportCompany);
            transaction.commit();
        }
    }

}
