package com.anudip.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subCode")
    private Long subCode;

    @Column(name = "subName")
    private String subName;

    @ManyToOne
    @JoinColumn(name = "lecturerId")
    private Lecturer lecturer;
    
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

	// Constructor
    public Subject(String subName) {
		super();
		this.subName = subName;
	}

    public Subject() {
		super();
	}

	// Getters and setters

   

    public String getSubName() {
        return subName;
    }

    public Long getSubCode() {
		return subCode;
	}

	public void setSubCode(Long subCode) {
		this.subCode = subCode;
	}

	public void setSubName(String subName) {
        this.subName = subName;
    }

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}