package swe.mobira.entities.net;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NetViewModel extends AndroidViewModel {
    private final NetRepository repository;
    private final LiveData<List<Net>> nets;

    public NetViewModel(@NonNull Application application, int siteID) {
        super(application);
        repository = new NetRepository(application);
        nets = repository.getNetsBySiteID(siteID);
    }

    public void insertNet(Net net) {
        repository.insertNet(net);
    }

    public void updateNet(Net net) {
        repository.updateNet(net);
    }

    public void deleteNet(Net net) {
        repository.deleteNet(net);
    }

    public LiveData<Net> getNetByID(int netID) {
        return repository.getNetByID(netID);
    }

    public LiveData<List<Net>> getNetsBySiteID(int siteID) {
        return nets;
    }
}
