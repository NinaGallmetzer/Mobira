package swe.mobira.entities.site;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.MobiraDatabase;

// REPOSITORY (https://www.youtube.com/watch?v=HhmA9S53XV8)
// useful in case of multiple data sources (e.g. locally and cloud)
public class SiteRepository {
    private SiteDAO siteDAO;
    private LiveData<List<Site>> allSites;

    public SiteRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        siteDAO = database.siteDAO();
        allSites = siteDAO.getAllSites();
    }

    // to not run function on the main thread > use ExecutorService created in the database.class
    public void insertSite(Site site) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            siteDAO.insertSite(site);
        });
    }

    public void updateSite(Site site) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            siteDAO.updateSite(site);
        });
    }

    public void deleteSite(Site site) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            siteDAO.deleteSite(site);
        });
    }

    public LiveData<Site> getSiteByID(int siteID) {
        return siteDAO.getSiteByID(siteID);
    }

    public LiveData<List<Site>> getAllSites() {
        return allSites;
    }
}
