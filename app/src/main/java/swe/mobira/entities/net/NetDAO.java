package swe.mobira.entities.net;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NetDAO {
    @Insert
    void insertNet(Net net);

    @Update
    void updateNet(Net net);

    @Delete
    void deleteNet(Net net);

    @Query("DELETE FROM Net")
    void deleteAllNets();

    @Transaction
    @Query("SELECT * FROM Net WHERE siteID = :siteID ORDER BY netNumber")
    LiveData<List<Net>> getNetsBySiteID(int siteID);

    @Transaction
    @Query("SELECT * FROM Net WHERE netID = :netID")
    LiveData<Net> getNetByID(int netID);

}
