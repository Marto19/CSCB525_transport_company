package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.TripDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TripDetailsDAO {

    /**
     * Creates a new TripDetails in the database.
     *
     * @param tripDetails The TripDetails object to be created.
     * @throws IllegalArgumentException If the provided tripDetails object is null.
     */
    public static void createTripDetails(TripDetails tripDetails) {
        if (tripDetails == null) {
            throw new IllegalArgumentException("The trip details cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(tripDetails);
            transaction.commit();
        }
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
     * Deletes a TripDetails by its ID.
     *
     * @param id The ID of the TripDetails to delete.
     */
    public static void deleteTripDetailsById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            TripDetails tripDetailsToDelete = session.get(TripDetails.class, id);

            if (tripDetailsToDelete != null) {
                session.delete(tripDetailsToDelete);
                transaction.commit();
            } else {
                System.out.println("TripDetails with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting trip details: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all TripDetails from the database.
     *
     * @return List of TripDetails objects representing all trip details in the database.
     */
    public static List<TripDetails> getAllTripDetails() {
        List<TripDetails> tripDetailsList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            tripDetailsList = session.createQuery("select td from TripDetails td", TripDetails.class)
                    .getResultList();
            transaction.commit();
        }
        return tripDetailsList;
    }

}
