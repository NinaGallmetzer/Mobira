package swe.mobira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import swe.mobira.entities.RingingRecord;
import swe.mobira.entities.Site;

@Database(entities = {Site.class,
        RingingRecord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDAO appDAO();
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "mobira_database")
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
                AppDAO appDAO = INSTANCE.appDAO();
                appDAO.deleteAllSites();

                appDAO.insertSite(new Site("Site title 1", "Site description 1", 1.11, 11.1, "Site comment 1"));
                appDAO.insertSite(new Site("Site title 2", "Site description 2", 2.22, 22.2, "Site comment 2"));
                appDAO.insertSite(new Site("Site title 3", "Site description 3", 3.33, 33.3, "Site comment 3"));
                appDAO.insertRingingRecord(new RingingRecord(2,4353,2525,3232,15.4, 23.7, "sunny", "Record comment 1"));
            });
        }
    };
}
