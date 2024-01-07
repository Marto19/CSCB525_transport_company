package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.VehicleType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleTypeDAO {
    /**
     * Adds a new VehicleType to the database.
     *
     * @param vehicleType The VehicleType object to be added.
     */
    public static void addVehicleType(VehicleType vehicleType) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // it used to be save(), but it's deprecated
            session.persist(vehicleType);
            transaction.commit();
        }
    }
    /**
     * Deletes a VehicleType from the database.
     *
     * @param vehicleType The VehicleType object to be deleted.
     */
    public static void deleteVehicleType(VehicleType vehicleType){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            // it used to be delete(), but it's deprecated
            session.remove(vehicleType);
            transaction.commit();
        }
    }
    /**
     * Retrieves a VehicleType from the database by its id.
     *
     * @param id The id of the VehicleType to be retrieved.
     * @return The VehicleType object with the given id.
     */
    public static VehicleType getVehicleTypeById(long id) {
        VehicleType vehicleType;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicleType = session.get(VehicleType.class, id);
            transaction.commit();
        }
        return vehicleType;
    }
    /**
     * Saves or updates a VehicleType in the database.
     *
     * @param vehicleType The VehicleType object to be saved or updated.
     */
    public static void saveOrUpdateVehicleType(VehicleType vehicleType) {
        if(vehicleType == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // it used to be saveOrUpdate(), but it's deprecated
            session.merge(vehicleType);
            transaction.commit();
        }
    }
    /**
     * Retrieves all VehicleTypes from the database.
     *
     * @return A list of all VehicleTypes.
     */
    public static List<VehicleType> getVehicleTypes() {
        List<VehicleType> vehicleType;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicleType = session.createQuery("Select vt From VehicleType vt", VehicleType.class)
                    .getResultList();
            transaction.commit();
        }
        return vehicleType;
    }

    //you can do this with a DTO
}
