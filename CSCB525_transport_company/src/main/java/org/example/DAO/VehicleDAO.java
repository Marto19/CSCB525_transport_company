package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {
    /**
     * Creates a new vehicle record in the database.
     *
     * @param vehicle The vehicle object to be created.
     * @throws IllegalArgumentException If the provided vehicle object is null.
     */
    public static void createVehicle(Vehicle vehicle){
        if(vehicle == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }
    }

    /**
     * Retrieves a list of all vehicles from the database.
     *
     * @return List of Vehicle objects representing all vehicles in the database.
     *         Returns an empty list if no vehicles are found.
     */
    public static List<Vehicle> getVehicle(){
        List<Vehicle> customerList;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            customerList = session.createQuery("select c from Vehicle c", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }
        return customerList;
    }


    /**
     * Updates an existing vehicle record in the database.
     *
     * @param vehicle The updated vehicle object.
     * @throws IllegalArgumentException If the provided vehicle object is null.
     */
    public static void updateVehicle(Vehicle vehicle){
        if(vehicle == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }
    }

    /**
     * Deletes a vehicle record from the database.
     *
     * @param vehicle The vehicle object to be deleted.
     * @throws IllegalArgumentException If the provided vehicle object is null.
     */
    public static void deleteVehicle(Vehicle vehicle){
        if (vehicle == null){
            throw new IllegalArgumentException("Employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }
}
