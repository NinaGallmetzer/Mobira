package swe.mobira.entities.ringingrecord;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.relationships.SiteWithRecords;

@Dao
public interface RingingRecordDAO {
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
    @Query("SELECT * FROM ringingrecord WHERE siteID = :siteID")
    LiveData<List<RingingRecord>> getRingingRecordsBySiteID(int siteID);
}
