package com.anudip.app.dao;

import com.anudip.app.entities.Lecturer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class LecturerDAO {
    private EntityManager entityManager;

    public LecturerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Lecturer lecturer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(lecturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Lecturer findById(Long lecturerId) {
        return entityManager.find(Lecturer.class, lecturerId);
    }

    public void update(Lecturer lecturer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(lecturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    private static void viewTeachers(LecturerDAO lecturerDAO) {
        System.out.println("\nTeachers:");
        List<Lecturer> teachers = lecturerDAO.getAllTeachers();
        for (Lecturer teacher : teachers) {
            System.out.println(teacher);
        }
    }

    public void delete(Lecturer lecturer) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(lecturer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Lecturer> getAllTeachers() {
        Query query = entityManager.createQuery("SELECT lc FROM Lecturer lc");
        return query.getResultList();
    }
    public List<Lecturer> getAllLecturerCopies() {
        Query query = entityManager.createQuery("SELECT lc FROM Lecturer lc");
        return query.getResultList();
    }
}
