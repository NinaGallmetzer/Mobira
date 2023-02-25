package swe.mobira.entities.site;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
    // DAO & ROOM DATABASE (https://www.youtube.com/watch?v=0cg09tlAAQ0)
    // LiveDate makes object observable > as soon as site table changes > object is updated >
    // > activity will using it will be notified
    // needs to be declared LiveData in every function using this function up until activity
    @Query("SELECT * FROM Site ORDER BY title ASC")
    LiveData<List<Site>> getAllSites();

    @Query("SELECT * FROM Site WHERE siteID = :siteID")
    LiveData<Site> getSiteByID(int siteID);
}
