package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Goods;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodsDAO {

    /**
     * Retrieves a Goods entity by its ID.
     *
     * @param id The ID of the Goods entity to retrieve.
     * @return The Goods entity with the specified ID, or null if not found.
     */
    public static Goods getGoodsById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Goods goods = session.get(Goods.class, id);
            transaction.commit();
            return goods;
        } catch (Exception e) {
            System.err.println("Error retrieving goods by ID: " + e.getMessage());
            return null;
        }
    }
    /**
     * Creates a new goods record in the database.
     *
     * @param goods The Goods object to be created.
     * @throws IllegalArgumentException If the provided goods object is null.
     */
    public static void createGoods(Goods goods) {
        if (goods == null) {
            throw new IllegalArgumentException("The goods object cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(goods);
            transaction.commit();
        }
    }

    /**
     * Retrieves a list of all goods from the database.
     *
     * @return List of Goods objects representing all goods in the database.
     *         Returns an empty list if no goods are found.
     */
    public static List<Goods> getGoods() {
        List<Goods> goodsList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsList = session.createQuery("select g from Goods g", Goods.class)
                    .getResultList();
            transaction.commit();
        }
        return goodsList;
    }

    /**
     * Updates an existing goods record in the database.
     *
     * @param goods The updated Goods object.
     * @throws IllegalArgumentException If the provided goods object is null.
     */
    public static void updateGoods(Goods goods) {
        if (goods == null) {
            throw new IllegalArgumentException("The goods object cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(goods);
            transaction.commit();
        }
    }

    /**
     * Deletes a goods record from the database.
     *
     * @param goods The Goods object to be deleted.
     * @throws IllegalArgumentException If the provided goods object is null.
     */
    public static void deleteGoods(Goods goods) {
        if (goods == null) {
            throw new IllegalArgumentException("Goods object cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(goods);
            transaction.commit();
        }
    }
}
