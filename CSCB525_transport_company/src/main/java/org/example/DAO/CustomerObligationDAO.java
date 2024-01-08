//package org.example.DAO;
//
//import org.example.configuration.SessionFactoryUtil;
//import org.example.entity.CustomerObligation;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class CustomerObligationDAO {
//    /**
//     * Creates a new CustomerObligation in the database.
//     *
//     * @param customerObligation The CustomerObligation object to be created.
//     * @throws IllegalArgumentException If the provided customerObligation object is null.
//     */
//    public static void createCustomerObligation(CustomerObligation customerObligation) {
//        if (customerObligation == null) {
//            throw new IllegalArgumentException("The customer obligation cannot be null");
//        }
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.persist(customerObligation);
//            transaction.commit();
//        }
//    }
//
//    /**
//     * Updates an existing trip record in the database.
//     *
//     * @param customerObligation The updated TripDetails object.
//     * @throws IllegalArgumentException If the provided trip object is null.
//     */
//    public static void saveOrUpdateCustomerObligation(CustomerObligation customerObligation){
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            // it used to be saveOrUpdate(), but it's deprecated
//            session.merge(customerObligation);
//            transaction.commit();
//        }
//    }
//
//    /**
//     * Retrieves a CustomerObligation from the database by its ID.
//     *
//     * @param id The ID of the CustomerObligation to be retrieved.
//     * @return The CustomerObligation object with the given ID, or null if not found.
//     */
//    public static CustomerObligation getCustomerObligationById(long id) {
//        CustomerObligation customerObligation;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            customerObligation = session.get(CustomerObligation.class, id);
//            transaction.commit();
//        }
//        return customerObligation;
//    }
//
//    /**
//     * Deletes a CustomerObligation by its ID.
//     *
//     * @param id The ID of the CustomerObligation to delete.
//     */
//    public static void deleteCustomerObligationById(long id) {
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            CustomerObligation customerObligationToDelete = session.get(CustomerObligation.class, id);
//
//            if (customerObligationToDelete != null) {
//                session.delete(customerObligationToDelete);
//                transaction.commit();
//            } else {
//                System.out.println("Customer Obligation with ID " + id + " not found.");
//            }
//        } catch (Exception e) {
//            System.err.println("Error deleting customer obligation: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Retrieves a list of all CustomerObligations from the database.
//     *
//     * @return List of CustomerObligation objects representing all customer obligations in the database.
//     */
//    public static List<CustomerObligation> getAllCustomerObligations() {
//        List<CustomerObligation> customerObligationList;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            customerObligationList = session.createQuery("select co from CustomerObligation co", CustomerObligation.class)
//                    .getResultList();
//            transaction.commit();
//        }
//        return customerObligationList;
//    }
//}
