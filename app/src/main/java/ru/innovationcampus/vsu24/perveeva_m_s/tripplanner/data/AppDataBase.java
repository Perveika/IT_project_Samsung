package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;

@Database(entities = {Catalog.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DataDao dataDao();
}
