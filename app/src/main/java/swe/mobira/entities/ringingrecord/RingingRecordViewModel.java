package swe.mobira.entities.ringingrecord;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.entities.site.Site;


public class RingingRecordViewModel extends AndroidViewModel {
    private RingingRecordRepository repository;
    private LiveData<List<RingingRecord>> ringingRecords;

    public RingingRecordViewModel(@NonNull Application application, int siteID) {
        super(application);
        repository = new RingingRecordRepository(application);
        ringingRecords = repository.getRingingsRecordsBySiteID(siteID);
    }

    public void insertRingingRecord(RingingRecord ringingRecord) {
        repository.insertRingingRecord(ringingRecord);
    }

    public void updateRingingRecord(RingingRecord ringingRecord) {
        repository.updateRingingRecord(ringingRecord);
    }

    public void deleteRingingRecord(RingingRecord ringingRecord) {
        repository.deleteRingingRecord(ringingRecord);
    }

    public LiveData<RingingRecord> getRingingRecordByID(int recordID) {
        return repository.getRingingRecordByID(recordID);
    }

    public LiveData<List<RingingRecord>> getRingingRecordsBySiteID(int siteID) {
        return ringingRecords;
    }
}
