package com.nitin.k5.attendance.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance_records")
public class AttendanceRecord {
    @PrimaryKey
    public long id;
    
    public String date;
    public String day;
    public String status; // PRESENT, ABSENT, LEAVE
    public String time;
    public long timestamp;
    
    public AttendanceRecord() {
    }
    
    public AttendanceRecord(String date, String day, String status, String time, long timestamp) {
        this.date = date;
        this.day = day;
        this.status = status;
        this.time = time;
        this.timestamp = timestamp;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDay() {
        return day;
    }
    
    public void setDay(String day) {
        this.day = day;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
