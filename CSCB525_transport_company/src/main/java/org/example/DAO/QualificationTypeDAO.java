package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.QualificationType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QualificationTypeDAO {
    /**
     * Adds a QualificationType entity to the database.
     *
     * @param qualificationType The QualificationType entity to be added.
     */
    public static void addQualificationType(QualificationType qualificationType){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(qualificationType);
            transaction.commit();
        }
    }

    /**
     * Retrieves a QualificationType entity from the database by its ID.
     *
     * @param id The ID of the QualificationType entity to be retrieved.
     * @return The QualificationType entity with the specified ID.
     */
    public static QualificationType getQualificationTypeById(long id) {
        QualificationType qualificationType;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            qualificationType = session.get(QualificationType.class, id);
            transaction.commit();
        }
        return qualificationType;
    }

    /**
     * Updates an existing QualificationType entity in the database or saves it if it does not exist.
     *
     * @param qualificationType The QualificationType entity to be updated or saved.
     */
    public static void saveOrUpdateQualificationType(QualificationType qualificationType) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(qualificationType);
            transaction.commit();
        }
    }

    /**
     * Deletes a QualificationType entity from the database.
     *
     * @param qualificationType The QualificationType entity to be deleted.
     */
    public static void deleteQualificationType(QualificationType qualificationType){
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(qualificationType);
            transaction.commit();
        }
    }

    /**
     * Retrieves all QualificationType entities from the database.
     *
     * @return A list of all QualificationType entities in the database.
     */
    public static List<QualificationType> getQualificationType() {
        List<QualificationType> qualificationTypes;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            qualificationTypes = session
                    .createQuery("Select qt From QualificationType qt", QualificationType.class)
                    .getResultList();
            transaction.commit();
        }
        return qualificationTypes;
    }
}
