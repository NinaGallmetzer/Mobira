package swe.mobira;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDAO {

    @Insert
    void insertSite(Site site);

    @Update
    void updateSite(Site site);

    @Delete
    void deleteSite(Site site);

    @Query("DELETE FROM site_table")
    void deleteAllSites();

    @Query("SELECT * FROM site_table ORDER by title ASC")
    LiveData<List<Site>> getAllSites();
}
