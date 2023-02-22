package swe.mobira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.ringingrecord.RingingRecordDAO;
import swe.mobira.entities.site.Site;
import swe.mobira.entities.site.SiteDAO;

@Database(entities = {Site.class,
        RingingRecord.class}, version = 1)
public abstract class MobiraDatabase extends RoomDatabase {
    public abstract SiteDAO siteDAO();
    public abstract RingingRecordDAO ringingRecordDAO();
    private static volatile MobiraDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MobiraDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobiraDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MobiraDatabase.class, "mobira_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                SiteDAO siteDAO = INSTANCE.siteDAO();
                siteDAO.deleteAllSites();
                RingingRecordDAO ringingRecordDAO = INSTANCE.ringingRecordDAO();
                ringingRecordDAO.deleteAllRingingRecords();

                siteDAO.insertSite(new Site("Site title 1", "Site description 1", 1.11, 11.1, "Site comment 1"));
                siteDAO.insertSite(new Site("Site title 2", "Site description 2", 2.22, 22.2, "Site comment 2"));
                siteDAO.insertSite(new Site("Site title 3", "Site description 3", 3.33, 33.3, "Site comment 3"));
                ringingRecordDAO.insertRingingRecord(new RingingRecord(2,4353,2525,3232,15.4, 23.7, "sunny", "Record comment 1"));
            });
        }
    };
}
