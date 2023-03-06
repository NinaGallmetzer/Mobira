package swe.mobira.entities.birdrecord;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BirdRecordDAO {
    @Insert
    void insertBirdRecord(BirdRecord birdRecord);

    @Update
    void updateBirdRecord(BirdRecord birdRecord);

    @Delete
    void deleteBirdRecord(BirdRecord birdRecord);

    @Query("DELETE FROM BirdRecord")
    void deleteAllBirdRecords();

    @Transaction
    @Query("SELECT * FROM BirdRecord WHERE ringingRecordID = :ringingRecordID ORDER BY birdRecordID")
    LiveData<List<BirdRecord>> getBirdRecordsByRingingRecordID(int ringingRecordID);

    @Transaction
    @Query("SELECT * FROM BirdRecord WHERE birdRecordID = :birdRecordID")
    LiveData<BirdRecord> getBirdRecordByID(int birdRecordID);

}
