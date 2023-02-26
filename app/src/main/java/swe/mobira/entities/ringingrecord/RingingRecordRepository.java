package swe.mobira.entities.ringingrecord;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.MobiraDatabase;
import swe.mobira.entities.site.Site;

public class RingingRecordRepository {
    private RingingRecordDAO ringingRecordDAO;
    private LiveData<List<RingingRecord>> ringingRecords;
    private int siteID;

    public RingingRecordRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        ringingRecordDAO = database.ringingRecordDAO();
        ringingRecords = ringingRecordDAO.getRingingRecordsBySiteID(siteID);
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

    public LiveData<RingingRecord> getRingingRecordByID(int recordID) {
        return ringingRecordDAO.getRingingRecordByID(recordID);
    }

    public LiveData<List<RingingRecord>> getRingingsRecordsBySiteID(int siteID) {
        return ringingRecordDAO.getRingingRecordsBySiteID(siteID);
    }
}
