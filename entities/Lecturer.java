package com.anudip.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "lecturer")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturerID")
    private Long lecturerId;

    @Column(name = "lecturerName")
    private String lecturerName;
    
    public Lecturer(String lecturerName) {
		super();
		this.lecturerName = lecturerName;
	}

    public Lecturer() {
		super();
	}

	// Getters and setters
    public Long getLecturerId() {
        return lecturerId;
    }

	public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

	@Override
	public String toString() {
		return "Lecturer [lecturerId=" + lecturerId + ", lecturerName=" + lecturerName + "]";
	}

//    public Long getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Long courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getSubjectCode() {
//        return subjectCode;
//    }
//
//    public void setSubjectCode(String subjectCode) {
//        this.subjectCode = subjectCode;
//    }

	
    
}
