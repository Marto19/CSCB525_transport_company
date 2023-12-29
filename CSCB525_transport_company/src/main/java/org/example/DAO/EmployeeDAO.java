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

            try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();

                TransportCompany company = employee.getTransportCompany();

                if (company != null) {
                    TransportCompany existingCompany = session.get(TransportCompany.class, company.getIdTransportCompany());

                    if (existingCompany == null) {
                        // If the company doesn't exist, save it before saving the employee
                        session.save(company);
                    } else {
                        // Use the existing company from the database
                        employee.setTransportCompany(existingCompany);
                    }
                }

                session.save(employee);
                transaction.commit();

            } catch (Exception e) {
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
                session.saveOrUpdate(employee);
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

//        public static void addEmployeeToCompany(Employee employee, TransportCompany company) {
//            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//
//                // Check if the employee already exists in the Employee entity table
//                Employee existingEmployee = session.createQuery("FROM Employee WHERE id = :id", Employee.class)
//                        .setParameter("employeeId", employee.getId())
//                        .uniqueResult();
//
//                if (existingEmployee == null) {
//                    // Associate the employee with the company and save the employee
//                    employee.setTransportCompany(company); // Set the transport company for the employee
//                    session.save(employee);
//
//                    transaction.commit();
//                    System.out.println("Employee added to the Employee entity table with the specified company.");
//                } else {
//                    System.out.println("Employee already exists in the Employee entity table.");
//                }
//            } catch (Exception e) {
//                System.err.println("Error adding employee to the Employee entity table: " + e.getMessage());
//            }
//        }

//        public static void addEmployeeToCompanyId(Employee employee, long id) {
//            if (employee == null) {
//                throw new IllegalArgumentException("The employee cannot be null");
//            }
//
//            try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//
//                TransportCompany company = session.get(TransportCompany.class, id);
//
//                if (company != null) {
//                    Query query = session.createQuery("SELECT COUNT(*) FROM Employee WHERE id = :employeeId AND transportCompany.id = :companyId");
//                    query.setParameter("employeeId", employee.getId());
//                    query.setParameter("companyId", id);
//
//                    long count = (long) query.uniqueResult();
//                    if (count == 0) {
//                        // Employee does not exist for this company, proceed to add
//                        employee.setTransportCompany(company);
//                        session.saveOrUpdate(employee);
//                        transaction.commit();
//                        System.out.println("Employee updated with new company ID.");
//                    } else {
//                        System.out.println("Employee with ID " + employee.getId() + " already exists for company with ID " + id);
//                    }
//                } else {
//                    System.out.println("TransportCompany with ID " + id + " does not exist.");
//                }
//            } catch (Exception e) {
//                System.err.println("Error adding employee to TransportCompany: " + e.getMessage());
//            }
//        }

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

    }
