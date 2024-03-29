package com.anudip.app.dao;

import com.anudip.app.entities.Subject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class SubjectDAO {
    private EntityManager entityManager;

    public SubjectDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Subject subject) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Subject findBySubCode(String subCode) {
        return entityManager.find(Subject.class, subCode);
    }

    public void update(Subject subject) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    private static void viewSubjects(SubjectDAO subjectDAO) {
        System.out.println("\nSubjects:");
        List<Subject> subjects = subjectDAO.getAllSubjects();
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }

    public void delete(Subject subject) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(subject);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubjects() {
        Query query = entityManager.createQuery("SELECT s FROM Subject s");
        return query.getResultList();
    }
}
