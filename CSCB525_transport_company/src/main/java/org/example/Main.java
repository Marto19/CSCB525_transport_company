package org.example;

import org.example.DAO.TransportCompanyDAO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TransportCompany;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SessionFactoryUtil.getSessionFactory().openSession();

        TransportCompany transportCompany = new TransportCompany("SAP");
//        TransportCompanyDAO.createCompany(transportCompany);

        TransportCompanyDAO.deleteCompany(transportCompany);
    }
}

//TODO: add client table, think about it, connect it with the payments