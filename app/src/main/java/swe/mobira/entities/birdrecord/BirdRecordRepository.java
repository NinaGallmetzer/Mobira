package swe.mobira.entities.birdrecord;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.MobiraDatabase;

public class BirdRecordRepository {
    private final BirdRecordDAO birdRecordDAO;
    private final LiveData<List<BirdRecord>> birdRecords;
    private int ringingRecordID;

    public BirdRecordRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        birdRecordDAO = database.birdRecordDAO();
        birdRecords = birdRecordDAO.getBirdRecordsByRingingRecordID(ringingRecordID);
    }

    public void insertBirdRecord(BirdRecord birdRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            birdRecordDAO.insertBirdRecord(birdRecord);
        });
    }

    public void updateBirdRecord(BirdRecord birdRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            birdRecordDAO.updateBirdRecord(birdRecord);
        });
    }

    public void deleteBirdRecord(BirdRecord birdRecord) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            birdRecordDAO.deleteBirdRecord(birdRecord);
        });
    }

    public LiveData<BirdRecord> getBirdRecordByID(int recordID) {
        return birdRecordDAO.getBirdRecordByID(recordID);
    }

    public LiveData<List<BirdRecord>> getBirdRecordsByRingingRecordID(int ringingRecordID) {
        return birdRecordDAO.getBirdRecordsByRingingRecordID(ringingRecordID);
    }
}
