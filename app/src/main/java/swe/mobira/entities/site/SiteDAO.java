package swe.mobira.entities.site;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import swe.mobira.entities.site.Site;

@Dao
public interface SiteDAO {
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
}
