package com.anudip.app.dao;

import com.anudip.app.entities.AppliedStudent;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AppliedStudentDAO {
    private EntityManager entityManager;

    public AppliedStudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(AppliedStudent student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public AppliedStudent findById(Long studentId) {
        return entityManager.find(AppliedStudent.class, studentId);
    }

    public void update(AppliedStudent student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(AppliedStudent student) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
