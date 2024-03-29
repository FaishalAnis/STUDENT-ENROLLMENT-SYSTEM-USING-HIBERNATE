package com.anudip.app.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appliedStudent")
public class AppliedStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentID")
    private Long studentId;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNum")
    private String phoneNum;

    @Column(name = "name")
    private String name;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "courseID")
    private Long courseId;
    
    @Column (name = "AggregatePercentage")
    private Long aggregatePercentage;

    @Column(name = "status")
    private boolean status;
    
    //Constructor
    public AppliedStudent(String name, String address, String phoneNum, String dob, Long courseId, Long aggregatePercentage,
			boolean status) {
		this.address = address;
		this.phoneNum = phoneNum;
		this.name = name;
		this.dob = dob;
		this.courseId = courseId;
		this.aggregatePercentage = aggregatePercentage;
		this.status = status;
	}
    

    public AppliedStudent() {
	}

    // Getters and setters

	public Long getStudentId() {
        return studentId;
    } 

	public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


	public Long getAggregatePercentage() {
		return aggregatePercentage;
	}


	public void setAggregatePercentage(Long aggregatePercentage) {
		this.aggregatePercentage = aggregatePercentage;
	}

	
    
    
}
