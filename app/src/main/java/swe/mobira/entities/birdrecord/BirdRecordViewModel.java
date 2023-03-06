package swe.mobira.entities.birdrecord;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BirdRecordViewModel extends AndroidViewModel {
    private final BirdRecordRepository repository;
    private final LiveData<List<BirdRecord>> birdRecords;

    public BirdRecordViewModel(@NonNull Application application, int ringingRecordID) {
        super(application);
        repository = new BirdRecordRepository(application);
        birdRecords = repository.getBirdRecordsByRingingRecordID(ringingRecordID);
    }

    public void insertBirdRecord(BirdRecord birdRecord) {
        repository.insertBirdRecord(birdRecord);
    }

    public void updateBirdRecord(BirdRecord birdRecord) {
        repository.updateBirdRecord(birdRecord);
    }

    public void deleteBirdRecord(BirdRecord birdRecord) {
        repository.deleteBirdRecord(birdRecord);
    }

    public LiveData<BirdRecord> getBirdRecordByID(int birdRecordID) {
        return repository.getBirdRecordByID(birdRecordID);
    }

    public LiveData<List<BirdRecord>> getBirdRecordsByRingingRecordID(int ringingRecordID) {
        return birdRecords;
    }
}
