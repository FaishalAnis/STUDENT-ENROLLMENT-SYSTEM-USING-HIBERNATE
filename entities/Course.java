package com.anudip.app.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    private Long courseId;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "cutOffPercentage")
    private int cutOffPercentage;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    // Constructors, getters, setters...
    public Course() {
    }

    public Course(String courseName, int cutOffPercentage) {
        this.courseName = courseName;
        this.cutOffPercentage = cutOffPercentage;
        this.subjects = new ArrayList<>();
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCutOffPercentage() {
        return cutOffPercentage;
    }

    public void setCutOffPercentage(int cutOffPercentage) {
        this.cutOffPercentage = cutOffPercentage;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", cutOffPercentage=" + cutOffPercentage +
                '}';
    }
}