    package org.example.DAO;

    import org.example.DTO.EmployeeDTO;
    import org.example.configuration.SessionFactoryUtil;
    import org.example.entity.Employee;
    import org.example.entity.QualificationType;
    import org.example.entity.TransportCompany;
    import org.hibernate.HibernateException;
    import org.hibernate.Session;
    import org.hibernate.Transaction;
    import org.hibernate.query.Query;

    import javax.persistence.criteria.CriteriaBuilder;
    import javax.persistence.criteria.CriteriaQuery;
    import javax.persistence.criteria.Join;
    import javax.persistence.criteria.Root;
    import java.math.BigDecimal;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;
    import java.util.stream.Collectors;

    public class EmployeeDAO {

        /**
         * Persists a new Employee entity in the database.
         *
         * @param employee The Employee object to be persisted.
         * @throws IllegalArgumentException If the provided employee object is null.
         * @throws HibernateException       If there's an issue during the save operation.
         */
        public static void createEmployee(Employee employee){
            if(employee == null){
                throw new IllegalArgumentException("The employee cannot be null");
            }
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(employee);
                transaction.commit();
            }catch (Exception e) {
                System.err.println("Error creating employee: " + e.getMessage());
            }
        }

//        public static void createEmployee(Employee employee) {
//            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//                // save() it's deprecated
//                session.persist(employee);
//                transaction.commit();
//            }
//        }

        /**
         * Retrieves a list of all employees from the database.
         *
         * @return List of Employee objects representing all employees in the database.
         */
        public static List<Employee> getEmployees(){
            List<Employee> employeeList;
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                employeeList = session.createQuery("select c from Employee c", Employee.class)
                        .getResultList();
                transaction.commit();
            }
            return employeeList;
        }



        /**
         * Updates an existing employee record in the database.
         *
         * @param employee The updated employee object.
         * @throws IllegalArgumentException If the provided employee object is null.
         */
        public static void saveOrUpdateEmployee(Employee employee){
            if(employee == null){
                throw new IllegalArgumentException("The employee cannot be null");
            }
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(employee);
                transaction.commit();
            }
        }

//        /**
//         * Retrieves a list of EmployeeDTO objects from the database.
//         *
//         * @return A list of EmployeeDTO objects.
//         */
//        public static List<EmployeeDTO> getEmployeesDTO() {
//            List<EmployeeDTO> employees;
//            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//                employees = session
//                        .createQuery("select new org.example.DTO.EmployeeDTO(e.id, e.qualificationTypeSet, e.name, e.transportCompany) " +
//                                "from Employee e", EmployeeDTO.class)
//                        .getResultList();
//                transaction.commit();
//            }
//            return employees;
//        }         //todo: fix it


        /**
         * todo
         * @param drivingQualification
         * @param employee
         */
        public static void addDrivingQualificationToDriver(QualificationType drivingQualification, Employee employee) {
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                if(employee == null) {
                    employee = new Employee();
                }
                if(employee.getQualificationTypeSet() == null){
                    Set<QualificationType> qualificationTypes = new HashSet<>();
                    employee.setQualificationTypeSet(qualificationTypes);
                }
                employee.getQualificationTypeSet().add(drivingQualification);
                // if the qualification is not in the database => add it; same for the driver
                QualificationTypeDAO.saveOrUpdateQualificationType(drivingQualification);
                EmployeeDAO.saveOrUpdateEmployee(employee);

                transaction.commit();
            }
        }

        /**
         * Retrieves a list of drivers along with the count of completed trips for each driver.
         * A completed trip is defined as a trip where the arrival date is before the current date.
         *
         * @return A list of Object arrays containing driver names and their completed trip counts.
         *         Each Object array contains:
         *         - Index 0: String - Driver name
         *         - Index 1: Long - Completed trip count
         * @throws Exception if an error occurs during the retrieval process
         */
        public static List<Object[]> getDriversAndCompletedTripsCount() {
            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                String hql = "select e.name, count(td.id) " +
                        "from Employee e " +
                        "left join TripDetails td on td.employee.id = e.id " +
                        "where td.arrivalDate < current_date() " +
                        "group by e.id";

                Query<Object[]> query = session.createQuery(hql, Object[].class);
                return query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * Prints the list of drivers and their completed trip counts to the console.
         * It retrieves the information using 'getDriversAndCompletedTripsCount' method.
         * If the query returns null or encounters an error, it prints an appropriate message.
         */
        public static void printDriversAndCompletedTripsCount() {
            List<Object[]> result = getDriversAndCompletedTripsCount();
            if (result != null) {
                for (Object[] row : result) {
                    String employeeName = (String) row[0];
                    Long tripCount = (Long) row[1];
                    System.out.println("Employee: " + employeeName + ", Completed Trips: " + tripCount);
                }
            } else {
                System.out.println("Query returned null.");
            }
        }


        /**
         * Deletes an employee record from the database.
         *
         * @param employee The employee object to be deleted.
         * @throws IllegalArgumentException If the provided employee object is null.
         */
        public static void deleteEmployee(Employee employee){
            if (employee == null){
                throw new IllegalArgumentException("Employee cannot be null");
            }
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.delete(employee);
                transaction.commit();
            }
        }

        /**
         * Deletes an Employee by their ID.
         *
         * @param id The ID of the Employee to delete.
         */
        public static void deleteEmployeeById(long id) {
            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                // Retrieve the Employee by ID
                Employee employeeToDelete = session.get(Employee.class, id);

                if (employeeToDelete != null) {
                    // Delete the employee if found
                    session.delete(employeeToDelete);
                    transaction.commit();
                } else {
                    // Print a message if the employee with the given ID is not found
                    System.out.println("Employee with ID " + id + " not found.");
                }
            } catch (Exception e) {
                // Handle any exceptions occurred during deletion
                System.err.println("Error deleting employee: " + e.getMessage());
            }
        }


        /**
         * Adds an employee to a specific company based on their IDs.
         *
         * @param employee The employee to be added.
         * @param id       The ID of the company.
         */
        public static void addEmployeeToCompanyId(Employee employee, long id) {
            validateEmployee(employee);

            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                TransportCompany company = getTransportCompany(session, id);

                if (company != null) {
                    if (!isEmployeeExistsForCompany(session, employee.getId(), id)) {
                        addEmployeeToCompany(session, employee, company);
                        transaction.commit();
                        System.out.println("Employee updated with new company ID.");
                    } else {
                        System.out.println("Employee with ID " + employee.getId() + " already exists for company with ID " + id);
                    }
                } else {
                    System.out.println("TransportCompany with ID " + id + " does not exist.");
                }
            } catch (Exception e) {
                System.err.println("Error adding employee to TransportCompany: " + e.getMessage());
            }
        }

        /**
         * Validates if the provided employee object is not null.
         *
         * @param employee The employee object to be validated.
         * @throws IllegalArgumentException if the employee is null.
         */
        private static void validateEmployee(Employee employee) {
            if (employee == null) {
                throw new IllegalArgumentException("The employee cannot be null");
            }
        }
        /**
         * Retrieves a transport company by its ID.
         *
         * @param session The Hibernate session.
         * @param id      The ID of the transport company.
         * @return The TransportCompany object if found, otherwise null.
         */
        private static TransportCompany getTransportCompany(Session session, long id) {
            return session.get(TransportCompany.class, id);
        }
        /**
         * Checks if an employee exists for a specific company.
         *
         * @param session    The Hibernate session.
         * @param employeeId The ID of the employee.
         * @param companyId  The ID of the company.
         * @return True if the employee exists for the company, false otherwise.
         */
        private static boolean isEmployeeExistsForCompany(Session session, long employeeId, long companyId) {
            Query query = session.createQuery("SELECT COUNT(*) FROM Employee WHERE id = :employeeId AND transportCompany.id = :companyId");
            query.setParameter("employeeId", employeeId);
            query.setParameter("companyId", companyId);

            long count = (long) query.uniqueResult();
            return count > 0;
        }
        /**
         * Adds an employee to a company.
         *
         * @param session  The Hibernate session.
         * @param employee The employee to be added.
         * @param company  The company to which the employee will be added.
         */
        private static void addEmployeeToCompany(Session session, Employee employee, TransportCompany company) {
            employee.setTransportCompany(company);
            session.saveOrUpdate(employee);
        }
        //TODO: redo the method above

        /**
         * Retrieves a driver employee from the system by its ID.
         *
         * @param id The ID of the driver employee to be retrieved.
         * @return The driver employee with the specified ID, or null if not found.
         * @throws org.hibernate.HibernateException If there is an issue with the Hibernate operations.
         *                           Check the nested exceptions for specific details.
         *  @throws IllegalArgumentException If the provided ID is 0 or negative.
         * @since 1.0
         */
        public static Employee getDriverById(long id) {
            if ( id <= 0) {
                throw new IllegalArgumentException("Driver employee ID cannot be 0 or negative.");
            }
                Employee driverEmployee;
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                driverEmployee = session.get(Employee.class, id);
                transaction.commit();
            }
            return driverEmployee;
        }

        /**
         * This method retrieves all Employee entities from the database, transforms each Employee entity into an EmployeeDTO,
         * and returns a list of these EmployeeDTOs.
         *
         * @return A List of EmployeeDTO objects. Each EmployeeDTO contains the id, qualificationTypeSet, name, and transportCompany
         *         of an Employee entity.
         *
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<EmployeeDTO> getEmployeeDTO(){
            List<Employee> employees;
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                employees = session.createQuery("from Employee e", Employee.class).getResultList();
                transaction.commit();
            }
            return employees.stream()
                    .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
                    .collect(Collectors.toList());
        }
        /**
         * This method retrieves all Employee entities from the database with salaries between two specified values,
         * and returns a list of these Employees.
         *
         * @param bottom The lower bound of the salary range.
         * @param top The upper bound of the salary range.
         * @return A List of Employee objects with salaries between the bottom and top values.
         *
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<Employee> findEmployeesBetweenSalary(BigDecimal bottom, BigDecimal top){
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
                Root<Employee> root = criteriaQuery.from(Employee.class);

                criteriaQuery.select(root).where((criteriaBuilder.between(root.get("salary"),bottom, top)));

                Query<Employee> query = session.createQuery(criteriaQuery);
                return query.getResultList();
            }
        }




        /**
         * Retrieves all Employee entities from the database with a specific qualification type and transforms them into EmployeeDTOs.
         *
         * @param qualificationType The qualification type to filter employees by.
         * @return A List of EmployeeDTO objects with the specified qualification type.
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<EmployeeDTO> employeesFindByQualification(QualificationType qualificationType) {
            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
                Root<Employee> root = cr.from(Employee.class);
                Join<Employee, QualificationType> join = root.join("qualificationTypeSet");

                cr.select(root).where(cb.equal(join.get("name"), qualificationType.getName()));

                Query<Employee> query = session.createQuery(cr);
                List<Employee> employees = query.getResultList();

                return employees.stream()
                        .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
                        .collect(Collectors.toList());
            }
        }

        /**
         * Retrieves all Employee entities from the database with a specific qualification type ID and transforms them into EmployeeDTOs.
         *
         * @param id The ID of the qualification type to filter employees by.
         * @return A List of EmployeeDTO objects with the specified qualification type.
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<EmployeeDTO> employeesFindByQualificationById(long id) {
            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
                Root<Employee> root = cr.from(Employee.class);
                Join<Employee, QualificationType> join = root.join("qualificationTypeSet");

                cr.select(root).where(cb.equal(join.get("id"), id));

                Query<Employee> query = session.createQuery(cr);
                List<Employee> employees = query.getResultList();

                return employees.stream()
                        .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
                        .collect(Collectors.toList());
            }
        }


        /**
         * Retrieves all Employee entities from the database, ordered by salary in ascending order, and transforms them into EmployeeDTOs.
         *
         * @return A List of EmployeeDTO objects ordered by salary from lowest to highest.
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<EmployeeDTO> getOrderedEmployeesBySalaryAscending() {
            List<Employee> employees;
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                employees = session.createQuery("Select c From Employee c" +
                                " ORDER BY c.salary", Employee.class)
                        .getResultList();
                transaction.commit();
            }
            return employees.stream()
                    .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
                    .collect(Collectors.toList());
        }

        /**
         * Retrieves all Employee entities from the database, ordered by salary in descending order, and transforms them into EmployeeDTOs.
         *
         * @return A List of EmployeeDTO objects ordered by salary from highest to lowest.
         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
         */
        public static List<EmployeeDTO> getOrderedEmployeesBySalaryDescending() {
            List<Employee> employees;
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                employees = session.createQuery("Select c From Employee c" +
                                " ORDER BY c.salary DESC", Employee.class)
                        .getResultList();
                transaction.commit();
            }
            return employees.stream()
                    .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
                    .collect(Collectors.toList());
        }

//        /**
//         * Retrieves all Employee entities from the database, ordered by position in ascending order, and transforms them into EmployeeDTOs.
//         *
//         * @return A List of EmployeeDTO objects ordered by position.
//         * @throws javax.persistence.PersistenceException If there is an issue with the transaction or the database connection.
//         */
//        public static List<EmployeeDTO> getOrderedEmployeesByPositionAscending() {
//            List<Employee> employees;
//            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//                employees = session.createQuery("Select c From Employee c" +
//                                " ORDER BY CONCAT(c.positionType)", Employee.class)
//                        .getResultList();
//                transaction.commit();
//            }
//            return employees.stream()
//                    .map(e -> new EmployeeDTO(e.getId(), e.getQualificationTypeSet(), e.getName(), e.getTransportCompany()))
//                    .collect(Collectors.toList());
//        }



    }
