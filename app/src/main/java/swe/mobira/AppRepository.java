package swe.mobira;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.entities.Site;

public class AppRepository {
    private AppDAO appDAO;
    private LiveData<List<Site>> allSites;

    public AppRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        appDAO = database.appDAO();
        allSites = appDAO.getAllSites();
    }

    public void insertSite(Site site) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.insertSite(site);
        });
    }

    public void updateSite(Site site) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.updateSite(site);
        });
    }

    public void deleteSite(Site site) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.deleteSite(site);
        });
    }

    public void deleteAllSites() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            appDAO.deleteAllSites();
        });
    }

    public LiveData<List<Site>> getAllSites() {
        return allSites;
    }
}
