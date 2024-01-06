package org.example.configuration;

//import org.example.entity.Company;
//import org.example.entity.CompanyEvent;
//import org.example.entity.Employee;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(TransportCompany.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Vehicle.class);
            configuration.addAnnotatedClass(Salary.class);
            configuration.addAnnotatedClass(TripDetails.class);
            configuration.addAnnotatedClass(Goods.class);
            configuration.addAnnotatedClass(Goods.class);
            configuration.addAnnotatedClass(OrderDetails.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(GoodsType.class);
            configuration.addAnnotatedClass(VehicleType.class);
            configuration.addAnnotatedClass(QualificationType.class);
            configuration.addAnnotatedClass(CustomerObligation.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
