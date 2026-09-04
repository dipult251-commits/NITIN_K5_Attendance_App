package com.nitin.k5.attendance.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nitin.k5.attendance.model.AttendanceRecord;

import java.util.List;

@Dao
public interface AttendanceDao {
    @Insert
    long insertAttendance(AttendanceRecord record);
    
    @Update
    void updateAttendance(AttendanceRecord record);
    
    @Delete
    void deleteAttendance(AttendanceRecord record);
    
    @Query("SELECT * FROM attendance_records ORDER BY timestamp DESC")
    List<AttendanceRecord> getAllRecords();
    
    @Query("SELECT * FROM attendance_records WHERE date = :date")
    AttendanceRecord getRecordByDate(String date);
    
    @Query("SELECT * FROM attendance_records WHERE date LIKE :monthYear ORDER BY timestamp DESC")
    List<AttendanceRecord> getRecordsByMonth(String monthYear);
    
    @Query("SELECT COUNT(*) FROM attendance_records WHERE date LIKE :monthYear AND status = 'PRESENT'")
    int getPresentCount(String monthYear);
    
    @Query("SELECT COUNT(*) FROM attendance_records WHERE date LIKE :monthYear AND status = 'ABSENT'")
    int getAbsentCount(String monthYear);
    
    @Query("SELECT COUNT(*) FROM attendance_records WHERE date LIKE :monthYear AND status = 'LEAVE'")
    int getLeaveCount(String monthYear);
    
    @Query("SELECT COUNT(*) FROM attendance_records WHERE date LIKE :monthYear")
    int getTotalDays(String monthYear);
    
    @Query("DELETE FROM attendance_records")
    void deleteAllRecords();
}
