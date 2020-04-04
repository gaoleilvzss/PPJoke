package com.vinsuan.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vinsuan.libcommon.AppGlobals;
@Database(entities = {Cache.class},version = 1,exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
    private static final CacheDatabase database ;

    static {
         database = Room.databaseBuilder(AppGlobals.getApplication(), CacheDatabase.class, "cache")
                .allowMainThreadQueries().addMigrations(CacheDatabase.sMigration).build();

    }
    public static CacheDatabase get(){
        return database;
    }
    public abstract CacheDao getCache();

    static Migration sMigration = new Migration(1,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
