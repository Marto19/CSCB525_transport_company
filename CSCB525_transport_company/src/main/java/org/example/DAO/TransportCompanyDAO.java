package org.example.DAO;

import org.example.DTO.TransportCompanyDTO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.entity.TransportCompany;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
            session.persist(transportCompany);
            transaction.commit();
        }
    }
    /**
     * Retrieves a TransportCompany from the database by its id.
     *
     * @param id The id of the TransportCompany to be retrieved.
     * @return The TransportCompany object with the given id.
     */
    public static TransportCompany getTransportCompanyById(long id) {
        TransportCompany company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(TransportCompany.class, id);
            transaction.commit();
        }
        return company;
    }

    /**
     * Deletes a TransportCompany by its ID.
     *
     * @param id The ID of the TransportCompany to delete.
     */
    public static void deleteTransportCompanyById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve the TransportCompany by ID
            TransportCompany companyToDelete = session.get(TransportCompany.class, id);

            if (companyToDelete != null) {
                // Delete the company if found
                session.delete(companyToDelete);
                transaction.commit();
            } else {
                // Print a message if the company with the given ID is not found
                System.out.println("Company with ID " + id + " not found.");
            }
        } catch (Exception e) {
            // Handle any exceptions occurred during deletion
            System.err.println("Error deleting company: " + e.getMessage());
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
     * function that counts the companies in the table
     *
     * no parameters
     * @return companies count
     */
    public static int countCompanies(){
        return getCompanies().size();
    }

    /**
     * Retrieves a list of TransportCompanyDTO objects from the database.
     *
     * @return A list of TransportCompanyDTO objects.
     */
    public static List<TransportCompanyDTO> getCompaniesDTO() {
        List<TransportCompanyDTO> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery("select new org.example.DTO.TransportCompanyDTO(c.id, c.name) " +
                            "from TransportCompany c", TransportCompanyDTO.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
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
            // it used to be saveOrUpdate(), but it's deprecated
            session.merge(transportCompany);
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

    /**
     * Deletes a transport company by its ID.
     *
     * @param companyId The ID of the company to be deleted.
     * @implNote This method fetches the company by its provided ID and removes it from the database.
     * @implSpec If no company is found with the given ID, no action is taken.
     * @throws IllegalArgumentException If the provided ID is null or negative.
     * @throws HibernateException       If an issue occurs during Hibernate interaction.
     */
    public static void deleteCompanyById(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            TransportCompany companyToDelete = session.get(TransportCompany.class, companyId);
            if (companyToDelete != null) {
                session.delete(companyToDelete);
                transaction.commit();
                System.out.println("Company with ID " + companyId + " deleted successfully.");
            } else {
                System.out.println("Company with ID " + companyId + " does not exist.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting company: " + e.getMessage());
        }
    }


    /**
     * Saves or updates a TransportCompany in the database.
     *
     * @param transportCompany The TransportCompany object to be saved or updated.
     */
    public static void saveOrUpdateCompany(TransportCompany transportCompany){
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // it used to be saveOrUpdate(), but it's deprecated
            session.merge(transportCompany);
            transaction.commit();
        }
    }


    /**
     *
     * @param id
     * @return
     */
    public static Set<Employee> getCompanyEmployees(long id) {
        TransportCompany company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.createQuery(
                            "select c from TransportCompany c" +
                                    " join fetch c.employeeSet" +
                                    " where c.id = :id",
                            TransportCompany.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return company.getEmployeeSet();
    }
//    /**
//     * Retrieves a list of EmployeeDTO objects for a specific company from the database.
//     *
//     * @param id The id of the company.
//     * @return A list of EmployeeDTO objects.
//     */

//    public static List<EmployeeDTO> getCompanyEmployeesDTO(long id) {
//        List<EmployeeDTO> employees;
//        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            employees = session.createQuery(
//                            "select new org.example.DTO.EmployeeDTO(e.id, e.qualificationType, e.name, e.transportCompany) from Employee e" +
//                                    " join e.transportCompany c " +
//                                    "where c.id = :id",
//                            EmployeeDTO.class)
//                    .setParameter("id", id)
//                    .getResultList();
//            transaction.commit();
//        }
//        return employees;
//    }

    /**
     * Retrieves all TransportCompany entities from the database with a balance between two specified values.
     *
     * @param bottom The lower bound of the balance range.
     * @param top The upper bound of the balance range.
     * @return A List of TransportCompany objects with balances between the bottom and top values.
     * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
     */
    public static List<TransportCompany> companiesFindByBalanceBetween(BigDecimal bottom, BigDecimal top) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);

            cr.select(root).where(cb.between(root.get("balance"), bottom, top));

            Query<TransportCompany> query = session.createQuery(cr);
            return query.getResultList();
        }
    }
    /**
     * Retrieves all TransportCompany entities from the database with names starting with a specified string.
     *
     * @param name The string that the names of the TransportCompany entities should start with.
     * @return A List of TransportCompany objects with names starting with the specified string.
     * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
     */
    public static List<TransportCompany> companiesFindByNameStartingWith(String name) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TransportCompany> cr = cb.createQuery(TransportCompany.class);
            Root<TransportCompany> root = cr.from(TransportCompany.class);

            Predicate nameStartingWith = cb.like(root.get("name"), name + "%");

            cr.select(root).where(nameStartingWith);

            Query<TransportCompany> query = session.createQuery(cr);
            List<TransportCompany> companies = query.getResultList();
            return companies;
        }
    }
    /**
     * Retrieves all TransportCompany entities from the database, ordered by name in ascending order.
     *
     * @return A List of TransportCompany objects ordered by name.
     * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
     */
    public static List<TransportCompany> getOrderedCompaniesByName() {
        List<TransportCompany> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session.createQuery("Select c From TransportCompany c" +
                            " ORDER BY c.name", TransportCompany.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }
    /**
     * Retrieves all TransportCompany entities from the database, ordered by income in ascending order.
     *
     * @return A List of TransportCompany objects ordered by income.
     * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
     */
    public static List<TransportCompany> getOrderedCompaniesByIncome() {
        List<TransportCompany> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session.createQuery("Select c From TransportCompany c" +
                            " ORDER BY c.income", TransportCompany.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }
}
