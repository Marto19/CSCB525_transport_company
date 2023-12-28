package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TripDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TripDAO {
    /**
     * Creates a new trip record in the database.
     *
     * @param trip The TripDetails object to be created.
     * @throws IllegalArgumentException If the provided trip object is null.
     */
    public static void createTrip(TripDetails trip){
        if(trip == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(trip);
            transaction.commit();
        }
    }

    /**
     * Retrieves a list of all trip details from the database.
     *
     * @return List of TripDetails objects representing all trip details in the database.
     *         Returns an empty list if no trip details are found.
     */
    public static List<TripDetails> getTripDetails(){
        List<TripDetails> customerList;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            customerList = session.createQuery("select c from TripDetails ", TripDetails.class)
                    .getResultList();
            transaction.commit();
        }
        return customerList;
    }


    /**
     * Updates an existing trip record in the database.
     *
     * @param trip The updated TripDetails object.
     * @throws IllegalArgumentException If the provided trip object is null.
     */
    public static void updateVehicle(TripDetails trip){
        if(trip == null){
            throw new IllegalArgumentException("The employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(trip);
            transaction.commit();
        }
    }

    /**
     * Deletes a trip record from the database.
     *
     * @param trip The TripDetails object to be deleted.
     * @throws IllegalArgumentException If the provided trip object is null.
     */
    public static void deleteTrip(TripDetails trip){
        if (trip == null){
            throw new IllegalArgumentException("Employee cannot be null");
        }
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(trip);
            transaction.commit();
        }
    }

}
