package com.anudip.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.anudip.app.entities.Lecturer;

public class LecturerTest {

    @Test
    public void testLecturerConstructorAndGettersSetters() {
        // Test constructor with parameters
        String lecturerName = "Test Lecturer";
        Lecturer lecturer = new Lecturer(lecturerName);
        assertNotNull(lecturer);
        assertEquals(lecturerName, lecturer.getLecturerName());

        // Test default constructor
        Lecturer defaultLecturer = new Lecturer();
        assertNotNull(defaultLecturer);

        // Test setters and getters
        Long lecturerId = 1L;
        lecturer.setLecturerId(lecturerId);

        assertEquals(lecturerId, lecturer.getLecturerId());
    }
}
