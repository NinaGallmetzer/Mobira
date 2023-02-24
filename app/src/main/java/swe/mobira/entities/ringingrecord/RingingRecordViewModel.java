package swe.mobira.entities.ringingrecord;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RingingRecordViewModel extends AndroidViewModel {
    private RingingRecordRepository repository;
    private LiveData<List<RingingRecord>> allRingingRecords;

    public RingingRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new RingingRecordRepository(application);
        allRingingRecords = repository.getAllRingingRecords();
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

    public LiveData<List<RingingRecord>> getAllRingingRecords() {
        return allRingingRecords;
    }
}
