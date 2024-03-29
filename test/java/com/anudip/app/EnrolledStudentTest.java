package com.anudip.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.anudip.app.entities.Course;
import com.anudip.app.entities.EnrolledStudent;

public class EnrolledStudentTest {

    @Test
    public void testEnrolledStudentConstructorAndGettersSetters() {
        // Test constructor with parameters
        String studentName = "John Doe";
        EnrolledStudent enrolledStudent = new EnrolledStudent(studentName);
        assertNotNull(enrolledStudent);
        assertEquals(studentName, enrolledStudent.getStudentName());

        // Test default constructor
        EnrolledStudent defaultEnrolledStudent = new EnrolledStudent();
        assertNotNull(defaultEnrolledStudent);

        // Test setters and getters
        Long studentId = 1L;
        Course course = new Course("Test Course", 80); // Create a mock Course
        enrolledStudent.setStudentId(studentId);
        enrolledStudent.setCourse(course);

        assertEquals(studentId, enrolledStudent.getStudentId());
        assertEquals(course, enrolledStudent.getCourse());
    }

}
