package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner;

import android.app.Application;

import androidx.room.Room;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.data.AppDataBase;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.data.DataDao;

public class App extends Application {

    private AppDataBase database;
    private DataDao dataDao;
    private static App instance;
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
            AppDataBase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();

        dataDao = database.dataDao();
    }

    public AppDataBase getDatabase() {
        return database;
    }

    public void setDatabase(AppDataBase database) {
        this.database = database;
    }

    public DataDao getDataDao() {
        return dataDao;
    }

    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }
}
