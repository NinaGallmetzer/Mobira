package swe.mobira;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Site>> allSites;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        allSites = repository.getAllSites();
    }

    public void insertSite(Site site) {
        repository.insertSite(site);
    }

    public void updateSite(Site site) {
        repository.updateSite(site);
    }

    public void deleteSite(Site site) {
        repository.deleteSite(site);
    }

    public void deleteAllSites() {
        repository.deleteAllSites();
    }

    public LiveData<List<Site>> getAllSites() {
        return allSites;
    }
}
