package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {
    /**
     * Retrieves a Vehicle entity by its ID.
     *
     * @param id The ID of the Vehicle entity to retrieve.
     * @return The Vehicle entity with the specified ID, or null if not found.
     */
    public static Vehicle getVehicleById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Vehicle vehicle = session.get(Vehicle.class, id);
            transaction.commit();
            return vehicle;
        } catch (Exception e) {
            System.err.println("Error retrieving vehicle by ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a list of all Vehicles from the database.
     *
     * @return List of all Vehicles in the database.
     */
    public static List<Vehicle> getVehicles() {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session.createQuery("Select v From Vehicle v", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }
        return vehicles;
    }
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
            session.persist(vehicle);
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
    public static void saveOrUpdateVehicle(Vehicle vehicle){
        if(vehicle == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(vehicle);
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

    /**
     * Deletes a Vehicle by its ID.
     *
     * @param id The ID of the Vehicle to delete.
     */
    public static void deleteVehicleById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve the Vehicle by ID
            Vehicle vehicleToDelete = session.get(Vehicle.class, id);

            if (vehicleToDelete != null) {
                // Delete the vehicle if found
                session.delete(vehicleToDelete);
                transaction.commit();
            } else {
                // Print a message if the vehicle with the given ID is not found
                System.out.println("Vehicle with ID " + id + " not found.");
            }
        } catch (Exception e) {
            // Handle any exceptions occurred during deletion
            System.err.println("Error deleting vehicle: " + e.getMessage());
        }
    }

}
