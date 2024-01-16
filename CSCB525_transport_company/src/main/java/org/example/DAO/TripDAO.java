package org.example.DAO;

import org.example.DTO.CarriedOutTripsReferenceDTO;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.*;
import org.example.exceptions.EmployeeNotQualfiedException;
import org.example.exceptions.NoVehicleSetForThisTrip;
import org.example.exceptions.NullCompanyException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TripDAO {
    /**
     * Creates a new trip record in the database.
     *
     * @param trip The TripDetails object to be created.
     * @throws IllegalArgumentException If the provided trip object is null.
     */
    public static void saveOrUpdateTripDetails(TripDetails trip){
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
     * This method calculates the total price of all arrived trips for a specific transport company and sets the
     * company's income.
     *
     * <p>
     * It uses Hibernate's Session and Criteria API to create a query that:
     * <ul>
     * <li>Joins the TripDetails, OrderDetails, and TransportCompany entities.</li>
     * <li>Selects the sum of the priceToPay attribute from the OrderDetails entity.</li>
     * <li>Filters the results based on the transport company's ID and whether the trip has already arrived (arrival date is before the current date).</li>
     * </ul>
     * </p>
     *
     * @param company The transport company is passed, we need only the id, for which the total price of arrived trips is to be calculated.
     * @return The total price of all arrived trips for the specified transport company. If no arrived trips are found, it returns BigDecimal.ZERO.
     *
     * @throws org.hibernate.HibernateException If there is a problem executing the query or performing the transaction.
     * @throws java.lang.IllegalArgumentException If the companyId is null.
     */
    public static BigDecimal getTotalArrivedTripsPrice(TransportCompany company) {

        BigDecimal total = BigDecimal.ZERO;

        long companyId = company.getIdTransportCompany();

        if (companyId <= 0) {
            try {
                throw new NullCompanyException("Company ID cannot be null");
            } catch (NullCompanyException e) {
                throw new RuntimeException(e);
            }
        }

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);

            Root<TripDetails> tripRoot = cq.from(TripDetails.class);
            Join<TripDetails, OrderDetails> orderJoin = tripRoot.join("orderDetails");
            Join<TripDetails, TransportCompany> companyJoin = tripRoot.join("transportCompany");

            cq.select(cb.sum(orderJoin.get("priceToPay"))).where(cb.equal(companyJoin.get("idTransportCompany"), companyId), cb.lessThan(tripRoot.get("arrivalDate"), LocalDate.now()));

            Query<BigDecimal> query = session.createQuery(cq);
            List<BigDecimal> results = query.getResultList();

            if (!results.isEmpty() && results.get(0) != null) {
                total = results.get(0);
            }

            transaction.commit();
        }
        if (!(total.equals(BigDecimal.ZERO))){
            if (company.getIncome() == null){
                company.setIncome(total);
            }else {
                company.setIncome(company.getIncome().add(total));
            }
            TransportCompanyDAO.saveOrUpdateCompany(company);
        }
        return total;
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
            customerList = session.createQuery("select c from TripDetails c", TripDetails.class)
                    .getResultList();
            transaction.commit();
        }
        return customerList;
    }
    /**
     * Retrieves the count of completed trips.
     *
     * @return The count of completed trips.
     */
    public static int getCompletedTripsCountDTO() {
        int count;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarriedOutTripsReferenceDTO> cq = cb.createQuery(CarriedOutTripsReferenceDTO.class);

            Root<TripDetails> root = cq.from(TripDetails.class);
            cq.select(cb.construct(CarriedOutTripsReferenceDTO.class, root.get("arrivalDate"))).where(cb.lessThan(root.get("arrivalDate"), LocalDate.now()));

            Query<CarriedOutTripsReferenceDTO> query = session.createQuery(cq);
            count = query.getResultList().size();

            transaction.commit();
        }
        return count;
    }




    /**
     * Retrieves a list of completed trips.
     *
     * @return The list of completed trips as CarriedOutTripsReferenceDTO objects.
     */
    public static List<CarriedOutTripsReferenceDTO> getCompletedTripsDTO() {
        List<CarriedOutTripsReferenceDTO> completedTrips;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CarriedOutTripsReferenceDTO> cq = cb.createQuery(CarriedOutTripsReferenceDTO.class);

            Root<TripDetails> root = cq.from(TripDetails.class);
            cq.select(cb.construct(CarriedOutTripsReferenceDTO.class, root.get("arrivalDate"))).where(cb.lessThan(root.get("arrivalDate"), LocalDate.now()));

            Query<CarriedOutTripsReferenceDTO> query = session.createQuery(cq);
            completedTrips = query.getResultList();

            transaction.commit();
        }
        return completedTrips;
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
            cr.select(root).where(cb.like(root.get("endPoint"), "%" + destinationSubstring + "%"));

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
            cr.select(root).orderBy(cb.asc(root.get("endPoint")));

            Query<TripDetails> query = session.createQuery(cr);
            return query.getResultList();
        }
    }
    /**
     * This method checks if an employee is qualified to operate a specific vehicle for a trip.
     *
     * @param employee The employee whose qualifications are to be checked.
     * @param vehicleType The type of vehicle that is to be used for the trip.
     * @param tripDetails The details of the trip for which the employee and vehicle are being considered.
     * @return The employee if they are qualified for the vehicle.
     * @throws NoVehicleSetForThisTrip If no vehicle is set for the trip.
     * @throws EmployeeNotQualfiedException If the employee is not qualified for the vehicle.
     *
     * <p>
     * The method first checks if a vehicle is set for the trip. If no vehicle is set, it throws a
     * NoVehicleSetForThisTrip exception.
     * </p>
     *
     * <p>
     * Next, it checks if the employee is qualified for the vehicle. It does this by iterating over the
     * set of QualificationTypes of the employee. For each QualificationType, it checks if the id of the
     * QualificationType matches the id of the VehicleType of the Vehicle set for the trip. If it finds a match,
     * it sets a flag, isQualified, to true and breaks the loop.
     * </p>
     *
     * <p>
     * If no matching VehicleType is found after checking all QualificationTypes, it throws an
     * EmployeeNotQualfiedException. If a match is found, it returns the employee.
     * </p>
     */
    public static Employee getQualificatedEmployee(Employee employee, VehicleType vehicleType,
                                                   TripDetails tripDetails) throws NoVehicleSetForThisTrip, EmployeeNotQualfiedException {
        // Check if a vehicle is set for the trip
        if (tripDetails.getVehicle() == null) {
            throw new NoVehicleSetForThisTrip("No vehicle is set for this trip.");
        }

        // Check if the employee is qualified for the vehicle
        Set<QualificationType> qualifications = employee.getQualificationTypeSet();
        boolean isQualified = false;
        for(QualificationType qualification : qualifications) {
            if(qualification.getId() == tripDetails.getVehicle().getVehicleType1().getId()) {
                isQualified = true;
                break;
            }
        }

        if (!isQualified) {
            throw new EmployeeNotQualfiedException("The employee is not qualified for this vehicle. Vehicle id: "
                    + tripDetails.getVehicle().getId() + " Vehicle type id: "
                    + tripDetails.getVehicle().getVehicleType1().getId() + " Employee qualification id: "
                    + employee.getQualificationTypeSet());
        }

        return employee;
    }










}
