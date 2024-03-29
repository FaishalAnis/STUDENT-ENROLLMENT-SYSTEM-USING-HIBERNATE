package com.anudip.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.anudip.app.entities.AppliedStudent;

public class AppliedStudentTest {

    @Test
    public void testAppliedStudentConstructorAndGettersSetters() {
        // Test constructor with parameters
        String name = "John Doe";
        String address = "123 Main St";
        String phoneNum = "123-456-7890";
        String dob = "2000-01-01"; // Example date
        Long courseId = 1L;
        Long aggregatePercentage = 80L;
        boolean status = true;
        
        AppliedStudent appliedStudent = new AppliedStudent(name, address, phoneNum, dob, courseId, aggregatePercentage, status);
        assertNotNull(appliedStudent);
        assertEquals(name, appliedStudent.getName());
        assertEquals(address, appliedStudent.getAddress());
        assertEquals(phoneNum, appliedStudent.getPhoneNum());
        assertEquals(dob, appliedStudent.getDob());
        assertEquals(courseId, appliedStudent.getCourseId());
        assertEquals(aggregatePercentage, appliedStudent.getAggregatePercentage());
        assertEquals(status, appliedStudent.getStatus());

        // Test default constructor
        AppliedStudent defaultAppliedStudent = new AppliedStudent();
        assertNotNull(defaultAppliedStudent);

        // Test setters and getters
        Long studentId = 1L;
        appliedStudent.setStudentId(studentId);

        assertEquals(studentId, appliedStudent.getStudentId());
    }

}

