package com.anudip.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.anudip.app.entities.Course;
import com.anudip.app.entities.Subject;

public class CourseTest {

    @Test
    public void testCourseConstructorAndGettersSetters() {
        // Test constructor with parameters
        String courseName = "Test Course";
        int cutOffPercentage = 80;
        Course course = new Course(courseName, cutOffPercentage);
        assertNotNull(course);
        assertEquals(courseName, course.getCourseName());
        assertEquals(cutOffPercentage, course.getCutOffPercentage());

        // Test default constructor
        Course defaultCourse = new Course();
        assertNotNull(defaultCourse);

        // Test setters and getters
        Long courseId = 1L;
        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject("Subject 1");
        Subject subject2 = new Subject("Subject 2");
        subjects.add(subject1);
        subjects.add(subject2);
        course.setCourseId(courseId);
        course.setSubjects(subjects);

        assertEquals(courseId, course.getCourseId());
        assertEquals(subjects, course.getSubjects());
    }

    @Test
    public void testAddSubject() {
        // Prepare test data
        Course course = new Course();
        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject("Subject 1");
        Subject subject2 = new Subject("Subject 2");
        subjects.add(subject1);
        subjects.add(subject2);
        
        // Execute the method to test
        course.addSubject(subject1);
        course.addSubject(subject2);
        
        // Verify the result
        assertEquals(subjects, course.getSubjects());
    }
    
    @Test
    public void testToString() {
        // Prepare test data
        String courseName = "Test Course";
        int cutOffPercentage = 80;
        Course course = new Course(courseName, cutOffPercentage);
        Long courseId = 1L;
        course.setCourseId(courseId);
        
        // Execute the method to test
        String expectedString = "Course{courseId=1, courseName='Test Course', cutOffPercentage=80}";
        
        // Verify the result
        assertEquals(expectedString, course.toString());
    }
}
