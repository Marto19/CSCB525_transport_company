package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TripDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
     * Retrieves a TripDetails from the database by its ID.
     *
     * @param id The ID of the TripDetails to be retrieved.
     * @return The TripDetails object with the given ID, or null if not found.
     */
    public static TripDetails getTripDetailsById(long id) {
        TripDetails tripDetails;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            tripDetails = session.get(TripDetails.class, id);
            transaction.commit();
        }
        return tripDetails;
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
            session.merge(trip);
            transaction.commit();
        }
    }

    /**
     * Deletes a TripDetails entity by its ID.
     *
     * @param id The ID of the TripDetails entity to delete.
     */
    public static void deleteTripDetailById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            TripDetails tripDetailToDelete = session.get(TripDetails.class, id);
            if (tripDetailToDelete != null) {
                session.delete(tripDetailToDelete);
                transaction.commit();
            } else {
                System.out.println("Trip details with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting trip details by ID: " + e.getMessage());
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

    /**
     * Retrieves a list of TripDetails based on a destination substring using criteria query.
     *
     * @param destinationSubstring The substring to search for in the destination field.
     * @return List of TripDetails objects matching the destination substring.
     */
    public static List<TripDetails> tripDetailsFindByDestination(String destinationSubstring) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TripDetails> cr = cb.createQuery(TripDetails.class);
            Root<TripDetails> root = cr.from(TripDetails.class);
            cr.select(root).where(cb.like(root.get("destination"), "%" + destinationSubstring + "%"));

            Query<TripDetails> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    /**
     * Retrieves a list of TripDetails sorted lexicographically by destination.
     *
     * @return List of TripDetails objects ordered by destination.
     */
    public static List<TripDetails> getOrderedTripDetailsByDestination() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TripDetails> cr = cb.createQuery(TripDetails.class);
            Root<TripDetails> root = cr.from(TripDetails.class);
            cr.select(root).orderBy(cb.asc(root.get("destination")));

            Query<TripDetails> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

}
