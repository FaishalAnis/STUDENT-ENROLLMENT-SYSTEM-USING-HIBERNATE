package com.anudip.app.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EnrolledStudent")
public class EnrolledStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentID")
    private Long studentId;

    @Column(name = "StudentName")
    private String studentName;

    @OneToOne
    @JoinColumn (name="courseId")
    private Course course;


	// Constructor
    public EnrolledStudent() {
	}
    
    
    public EnrolledStudent(String studentName) {
		this.studentName = studentName;
	}

	// Constructors, getters, and setters
    public Long getStudentId() {
        return studentId;
    }

	public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}

}
