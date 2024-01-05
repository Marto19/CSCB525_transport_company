    package org.example.DAO;

    import org.example.configuration.SessionFactoryUtil;
    import org.example.entity.Employee;
    import org.example.entity.TransportCompany;
    import org.hibernate.HibernateException;
    import org.hibernate.Session;
    import org.hibernate.Transaction;
    import org.hibernate.query.Query;

    import java.util.List;

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
        public static void updateEmployee(Employee employee){
            if(employee == null){
                throw new IllegalArgumentException("The employee cannot be null");
            }
            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(employee);
                transaction.commit();
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

//        public static void addObligations(BigDecimal salary){
//
//        }

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

    }
