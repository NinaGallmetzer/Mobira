package swe.mobira.entities.ringingrecord;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.MobiraDatabase;

public class RingingRecordRepository {
    private RingingRecordDAO ringingRecordDAO;
    private LiveData<List<RingingRecord>> allRingingRecords;

    public RingingRecordRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        ringingRecordDAO = database.ringingRecordDAO();
        ringingRecordDAO = database.ringingRecordDAO();
/*
        LiveData<List<RingingRecord>> test = ringingRecordDAO.getAllRingingRecords();
        String test_text = String.format("All RingingRecords %s", test);
        Log.d("debug", test_text);
*/
    }

    public void insertRingingRecord(RingingRecord ringingRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            ringingRecordDAO.insertRingingRecord(ringingRecord);
        });
    }

    public void updateRingingRecord(RingingRecord ringingRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            ringingRecordDAO.updateRingingRecord(ringingRecord);
        });
    }

    public void deleteRingingRecord(RingingRecord ringingRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            ringingRecordDAO.deleteRingingRecord(ringingRecord);
        });
    }

    public LiveData<List<RingingRecord>> getAllRingingRecords() {
        return allRingingRecords;
    }
}
