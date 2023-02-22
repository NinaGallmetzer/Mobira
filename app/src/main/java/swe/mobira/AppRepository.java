package swe.mobira;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.entities.ringingrecord.RingingRecordDAO;
import swe.mobira.entities.site.Site;
import swe.mobira.entities.site.SiteDAO;

public class AppRepository {
    private SiteDAO siteDAO;
    private LiveData<List<Site>> allSites;
    private RingingRecordDAO ringingRecordDAO;
    private LiveData<List<Site>> allRingingRecords;

    public AppRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        siteDAO = database.siteDAO();
        allSites = siteDAO.getAllSites();
        ringingRecordDAO = database.ringingRecordDAO();
/*
        LiveData<List<RingingRecord>> test = ringingRecordDAO.getAllRingingRecords();
        String test_text = String.format("All RingingRecords %s", test);
        Log.d("debug", test_text);
*/
    }

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

    public void deleteAllSites() {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            siteDAO.deleteAllSites();
        });
    }

    public LiveData<List<Site>> getAllSites() {
        return allSites;
    }
}
