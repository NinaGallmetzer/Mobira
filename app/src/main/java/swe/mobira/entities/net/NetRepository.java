package swe.mobira.entities.net;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import swe.mobira.MobiraDatabase;

public class NetRepository {
    private NetDAO netDAO;
    private LiveData<List<Net>> nets;
    private int siteID;

    public NetRepository(Application application) {
        MobiraDatabase database = MobiraDatabase.getDatabase(application);
        netDAO = database.netDAO();
        nets = netDAO.getNetsBySiteID(siteID);
    }

    public void insertNet(Net net) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            netDAO.insertNet(net);
        });
    }

    public void updateNet(Net net) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            netDAO.updateNet(net);
        });
    }

    public void deleteNet(Net net) {
        MobiraDatabase.databaseWriteExecutor.execute(() -> {
            netDAO.deleteNet(net);
        });
    }

    public LiveData<Net> getNetByID(int recordID) {
        return netDAO.getNetByID(recordID);
    }

    public LiveData<List<Net>> getNetsBySiteID(int siteID) {
        return netDAO.getNetsBySiteID(siteID);
    }

}
