package com.nitin.k5.attendance.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nitin.k5.attendance.model.AttendanceRecord;

@Database(entities = {AttendanceRecord.class}, version = 1, exportSchema = false)
public abstract class AttendanceDatabase extends RoomDatabase {
    public abstract AttendanceDao attendanceDao();
    
    private static volatile AttendanceDatabase INSTANCE;
    
    public static AttendanceDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AttendanceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AttendanceDatabase.class,
                            "nitin_k5_attendance_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
