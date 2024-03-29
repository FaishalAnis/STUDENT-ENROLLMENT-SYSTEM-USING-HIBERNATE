package com.anudip.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.anudip.app.entities.Course;
import com.anudip.app.entities.Subject;

public class CourseDAO {
    private EntityManager entityManager;

    public CourseDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Course findById(Long courseId) {
        return entityManager.find(Course.class, courseId);
    }

    public void update(Course course) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    

    public void delete(long courseId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Course course = entityManager.find(Course.class, courseId);
            if (course != null) {
                transaction.begin();
                entityManager.remove(course);
                transaction.commit();
            } else {
                System.out.println("Course with ID " + courseId + " not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c", Course.class);
        return query.getResultList();
    }
    
    public List<Subject> getSubjectsInCourse(Course course){
    	return course.getSubjects();
    }
    
//    public List<Lecturer>getLecturerByCourse()
}
