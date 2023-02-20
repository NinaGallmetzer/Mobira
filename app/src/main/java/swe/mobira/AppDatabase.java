package swe.mobira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Site.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDAO siteDAO();
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
                AppDAO appDao = INSTANCE.siteDAO();
                appDao.deleteAllSites();

                appDao.insertSite(new Site("Title 1", "Description 1", 1.11, 11.1, "Comment 1"));
                appDao.insertSite(new Site("Title 2", "Description 2", 2.22, 22.2, "Comment 2"));
                appDao.insertSite(new Site("Title 3", "Description 3", 3.33, 33.3, "Comment 3"));
            });
        }
    };

}
