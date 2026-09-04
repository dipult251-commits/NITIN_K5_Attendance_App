package com.nitin.k5.attendance.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nitin.k5.attendance.database.AttendanceDao;
import com.nitin.k5.attendance.database.AttendanceDatabase;
import com.nitin.k5.attendance.model.AttendanceRecord;

import java.util.List;

public class AttendanceRepository {
    private AttendanceDao attendanceDao;
    private MutableLiveData<List<AttendanceRecord>> allRecords;
    private MutableLiveData<Boolean> operationSuccess;
    
    public AttendanceRepository(Application application) {
        AttendanceDatabase db = AttendanceDatabase.getDatabase(application);
        attendanceDao = db.attendanceDao();
        allRecords = new MutableLiveData<>();
        operationSuccess = new MutableLiveData<>();
    }
    
    public void insertAttendance(AttendanceRecord record) {
        new InsertAsyncTask(attendanceDao).execute(record);
    }
    
    public void updateAttendance(AttendanceRecord record) {
        new UpdateAsyncTask(attendanceDao).execute(record);
    }
    
    public void deleteAttendance(AttendanceRecord record) {
        new DeleteAsyncTask(attendanceDao).execute(record);
    }
    
    public void loadAllRecords() {
        new LoadAsyncTask(attendanceDao, allRecords).execute();
    }
    
    public MutableLiveData<List<AttendanceRecord>> getAllRecords() {
        return allRecords;
    }
    
    public AttendanceRecord getRecordByDate(String date) {
        try {
            return new GetByDateTask(attendanceDao).execute(date).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<AttendanceRecord> getRecordsByMonth(String monthYear) {
        try {
            return new GetByMonthTask(attendanceDao).execute(monthYear).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getPresentCount(String monthYear) {
        try {
            return new GetPresentCountTask(attendanceDao).execute(monthYear).get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getAbsentCount(String monthYear) {
        try {
            return new GetAbsentCountTask(attendanceDao).execute(monthYear).get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getLeaveCount(String monthYear) {
        try {
            return new GetLeaveCountTask(attendanceDao).execute(monthYear).get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getTotalDays(String monthYear) {
        try {
            return new GetTotalDaysTask(attendanceDao).execute(monthYear).get();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void deleteAllRecords() {
        new DeleteAllAsyncTask(attendanceDao).execute();
    }
    
    // AsyncTask Classes
    private static class InsertAsyncTask extends AsyncTask<AttendanceRecord, Void, Void> {
        private AttendanceDao dao;
        
        InsertAsyncTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Void doInBackground(AttendanceRecord... records) {
            dao.insertAttendance(records[0]);
            return null;
        }
    }
    
    private static class UpdateAsyncTask extends AsyncTask<AttendanceRecord, Void, Void> {
        private AttendanceDao dao;
        
        UpdateAsyncTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Void doInBackground(AttendanceRecord... records) {
            dao.updateAttendance(records[0]);
            return null;
        }
    }
    
    private static class DeleteAsyncTask extends AsyncTask<AttendanceRecord, Void, Void> {
        private AttendanceDao dao;
        
        DeleteAsyncTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Void doInBackground(AttendanceRecord... records) {
            dao.deleteAttendance(records[0]);
            return null;
        }
    }
    
    private static class LoadAsyncTask extends AsyncTask<Void, Void, List<AttendanceRecord>> {
        private AttendanceDao dao;
        private MutableLiveData<List<AttendanceRecord>> liveData;
        
        LoadAsyncTask(AttendanceDao dao, MutableLiveData<List<AttendanceRecord>> liveData) {
            this.dao = dao;
            this.liveData = liveData;
        }
        
        @Override
        protected List<AttendanceRecord> doInBackground(Void... voids) {
            return dao.getAllRecords();
        }
        
        @Override
        protected void onPostExecute(List<AttendanceRecord> records) {
            liveData.postValue(records);
        }
    }
    
    private static class GetByDateTask extends AsyncTask<String, Void, AttendanceRecord> {
        private AttendanceDao dao;
        
        GetByDateTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected AttendanceRecord doInBackground(String... dates) {
            return dao.getRecordByDate(dates[0]);
        }
    }
    
    private static class GetByMonthTask extends AsyncTask<String, Void, List<AttendanceRecord>> {
        private AttendanceDao dao;
        
        GetByMonthTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected List<AttendanceRecord> doInBackground(String... months) {
            return dao.getRecordsByMonth(months[0]);
        }
    }
    
    private static class GetPresentCountTask extends AsyncTask<String, Void, Integer> {
        private AttendanceDao dao;
        
        GetPresentCountTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Integer doInBackground(String... months) {
            return dao.getPresentCount(months[0]);
        }
    }
    
    private static class GetAbsentCountTask extends AsyncTask<String, Void, Integer> {
        private AttendanceDao dao;
        
        GetAbsentCountTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Integer doInBackground(String... months) {
            return dao.getAbsentCount(months[0]);
        }
    }
    
    private static class GetLeaveCountTask extends AsyncTask<String, Void, Integer> {
        private AttendanceDao dao;
        
        GetLeaveCountTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Integer doInBackground(String... months) {
            return dao.getLeaveCount(months[0]);
        }
    }
    
    private static class GetTotalDaysTask extends AsyncTask<String, Void, Integer> {
        private AttendanceDao dao;
        
        GetTotalDaysTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Integer doInBackground(String... months) {
            return dao.getTotalDays(months[0]);
        }
    }
    
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private AttendanceDao dao;
        
        DeleteAllAsyncTask(AttendanceDao dao) {
            this.dao = dao;
        }
        
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllRecords();
            return null;
        }
    }
}
