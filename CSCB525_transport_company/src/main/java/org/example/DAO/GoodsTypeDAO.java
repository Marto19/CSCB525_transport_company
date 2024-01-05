package org.example.DAO;

import org.example.configuration.SessionFactoryUtil;
import org.example.entity.GoodsType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GoodsTypeDAO {
    /**
     * Creates a new goods type record in the database.
     *
     * @param goodsType The GoodsType object to be created.
     * @throws IllegalArgumentException If the provided goodsType object is null.
     */
    public static void createGoodsType(GoodsType goodsType) {
        if (goodsType == null) {
            throw new IllegalArgumentException("The goods type cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(goodsType);
            transaction.commit();
        }
    }

    /**
     * Retrieves a list of all goods types from the database.
     *
     * @return List of GoodsType objects representing all goods types in the database.
     *         Returns an empty list if no goods types are found.
     */
    public static List<GoodsType> getGoodsTypes() {
        List<GoodsType> goodsTypeList;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            goodsTypeList = session.createQuery("select g from GoodsType g", GoodsType.class)
                    .getResultList();
            transaction.commit();
        }
        return goodsTypeList;
    }

    /**
     * Updates an existing goods type record in the database.
     *
     * @param goodsType The updated GoodsType object.
     * @throws IllegalArgumentException If the provided goodsType object is null.
     */
    public static void updateGoodsType(GoodsType goodsType) {
        if (goodsType == null) {
            throw new IllegalArgumentException("The goods type cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(goodsType);
            transaction.commit();
        }
    }

    /**
     * Deletes a goods type record from the database.
     *
     * @param goodsType The GoodsType object to be deleted.
     * @throws IllegalArgumentException If the provided goodsType object is null.
     */
    public static void deleteGoodsType(GoodsType goodsType) {
        if (goodsType == null) {
            throw new IllegalArgumentException("Goods type cannot be null");
        }
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(goodsType);
            transaction.commit();
        }
    }
}
