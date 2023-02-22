package swe.mobira;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import swe.mobira.entities.RingingRecord;
import swe.mobira.entities.Site;
import swe.mobira.relationships.SiteWithRecords;

@Dao
public interface AppDAO {
    @Insert
    void insertSite(Site site);
    @Update
    void updateSite(Site site);
    @Delete
    void deleteSite(Site site);
    @Query("DELETE FROM Site")
    void deleteAllSites();
    @Query("SELECT * FROM Site ORDER BY title ASC")
    LiveData<List<Site>> getAllSites();


    @Insert
    void insertRingingRecord(RingingRecord ringingRecord);
    @Update
    void updateRingingRecord(RingingRecord ringingRecord);
    @Delete
    void deleteRingingRecord(RingingRecord ringingRecord);
    @Query("DELETE FROM RingingRecord")
    void deleteAllRingingRecords();
    @Query("SELECT * FROM RingingRecord ORDER BY recordDate ASC")
    LiveData<List<RingingRecord>> getAllRingingRecords();

    @Transaction
    @Query("SELECT * FROM Site ORDER by title ASC")
    LiveData<List<SiteWithRecords>> getSiteWithRecords();

    @Transaction
    @Query("SELECT * FROM site WHERE siteID = :siteID")
    LiveData<List<SiteWithRecords>> getSiteWithRecordsBySiteID(int siteID);

}
