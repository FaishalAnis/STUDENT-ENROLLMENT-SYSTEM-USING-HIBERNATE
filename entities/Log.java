package com.anudip.app.entities;

import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogID")
    private Integer logID;

    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "LogType")
    private LogType logType;

    @Column(name = "LogMessage", columnDefinition = "TEXT")
    private String logMessage;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
    
    public Log (LogType logType, String logMessage, User user ) {
    	
    	this.logMessage = logMessage;
    	this.logType = logType;
    	this.user = user;
    	
    }
    @PrePersist
    protected void onCreate() {
        timestamp = new Timestamp(new Date().getTime());
    }

    // Getters and setters

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Log() {
    	
    }

	@Override
	public String toString() {
		return "Log [logID=" + logID + ", timestamp=" + timestamp + ", logType=" + logType + ", logMessage="
				+ logMessage + ", user=" + user + "]";
	}
    
}
