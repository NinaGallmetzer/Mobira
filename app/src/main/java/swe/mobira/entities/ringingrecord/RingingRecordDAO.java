package swe.mobira.entities.ringingrecord;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import swe.mobira.entities.site.Site;

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

    @Transaction
    @Query("SELECT * FROM RingingRecord WHERE siteID = :siteID ORDER BY recordDate")
    LiveData<List<RingingRecord>> getRingingRecordsBySiteID(int siteID);

    @Transaction
    @Query("SELECT * FROM RingingRecord WHERE recordID = :recordID")
    LiveData<RingingRecord> getRingingRecordByID(int recordID);
}
