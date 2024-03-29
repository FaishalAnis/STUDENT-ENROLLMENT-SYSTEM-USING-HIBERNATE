package com.anudip.app.dao;

import com.anudip.app.entities.EnrolledStudent;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EnrolledStudentDAO {
    private EntityManager entityManager;

    public EnrolledStudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(EnrolledStudent enrolledStudent) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(enrolledStudent);
            transaction.commit();
        } catch (Exception e) {
        	System.out.println("Exception while enrolling- "+e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EnrolledStudent findById(Long studentId) {
        return entityManager.find(EnrolledStudent.class, studentId);
    }

    public void update(EnrolledStudent enrolledStudent) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(enrolledStudent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(long enrolledStudentId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EnrolledStudent enrolledStudent = entityManager.find(EnrolledStudent.class, enrolledStudentId);
            if (enrolledStudent != null) {
                entityManager.remove(enrolledStudent);
                transaction.commit();
                System.out.println("Enrolled student with ID " + enrolledStudentId + " deleted successfully!");
            } else {
                System.out.println("Enrolled student with ID " + enrolledStudentId + " not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<EnrolledStudent> getAllEnrolledStudents() {
        TypedQuery<EnrolledStudent> query = entityManager.createQuery("SELECT e FROM EnrolledStudent e", EnrolledStudent.class);
        return query.getResultList();
    }
}