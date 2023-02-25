package swe.mobira.entities.site;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// VIEW MODEL (https://www.youtube.com/watch?v=JLwW5HivZg4)
// where data needed for specific activity is stored and processed
// survives configuration changes (e.g. recreation of screen after orientation change)
public class SiteViewModel extends AndroidViewModel {
    private SiteRepository repository;
    private LiveData<List<Site>> allSites;

    public SiteViewModel(@NonNull Application application) {
        super(application);
        repository = new SiteRepository(application);
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

    public LiveData<Site> getSiteByID(int siteID) {
        return repository.getSiteByID(siteID);
    }

    public LiveData<List<Site>> getAllSites() {
        return allSites;
    }
}
