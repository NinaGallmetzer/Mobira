package swe.mobira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import swe.mobira.entities.ringingrecord.RingingRecord;
import swe.mobira.entities.ringingrecord.RingingRecordDAO;
import swe.mobira.entities.site.Site;
import swe.mobira.entities.site.SiteDAO;

// DAO & ROOM DATABASE (https://www.youtube.com/watch?v=0cg09tlAAQ0)
// list all entities (tables) found in database in annotation
// version # changes in case  of changes in db structure (when already in production) >
// > in that case, also provide migration strategy here (exportSchema)
@Database(entities = {Site.class, RingingRecord.class}, version = 1)
public abstract class MobiraDatabase extends RoomDatabase {
    public abstract SiteDAO siteDAO();
    public abstract RingingRecordDAO ringingRecordDAO();
    private static volatile MobiraDatabase INSTANCE;
    // DAO & ROOM DATABASE (https://www.youtube.com/watch?v=0cg09tlAAQ0)
    // Room doesn't allow you to issue queries on the main thread
    // When Room queries return LiveData, the queries are automatically run
    // asynchronously on a background thread > create an ExecutorService with a fixed thread pool
    // that you will use to run database operations asynchronously on a background thread
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // DAO & ROOM DATABASE (https://www.youtube.com/watch?v=0cg09tlAAQ0)
    // creates singleton of db
    // use builder since database.class is abstract
    public static MobiraDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobiraDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MobiraDatabase.class, "MobiraDB")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // REPOSITORY (https://www.youtube.com/watch?v=HhmA9S53XV8)
    // if db missing or not created yet > create > prepopulate with testing data
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                SiteDAO siteDAO = INSTANCE.siteDAO();
                siteDAO.deleteAllSites();
                RingingRecordDAO ringingRecordDAO = INSTANCE.ringingRecordDAO();
                ringingRecordDAO.deleteAllRingingRecords();

                siteDAO.insertSite(new Site("Site title 1", "Test site 1", 1.11, 11.1, "Comment test site 1"));
                siteDAO.insertSite(new Site("Site title 2", "Test site 2", 2.22, 22.2, "Comment test site 2"));
                siteDAO.insertSite(new Site("Site title 3", "Test site 3", 3.33, 33.3, "Comment test site 3"));
                siteDAO.insertSite(new Site("Site title 4", "Test site 4", 4.44, 44.4, "Comment test site 4"));
                siteDAO.insertSite(new Site("Site title 5", "Test site 5", 5.55, 55.5, "Comment test site 5"));
                ringingRecordDAO.insertRingingRecord(new RingingRecord(1,"2023-01-01","07:00","13:00",15.4, 23.7, "sunny", 2, "Jane", "Test record 1 site 1"));
                ringingRecordDAO.insertRingingRecord(new RingingRecord(2,"2023-01-02","07:00","13:00",15.4, 23.7, "sunny", 1, "Anne", "Test record 1 site 2"));
                ringingRecordDAO.insertRingingRecord(new RingingRecord(2,"2023-01-03","07:00","13:00",15.4, 23.7, "sunny", 3, "Bob", "Test record 2 site 2"));
            });
        }
    };
}
