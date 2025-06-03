package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;

@Dao
public interface DataDao {
    @Query("SELECT * FROM Catalog")
    List<Catalog> getAll();

    @Query("SELECT * FROM Catalog")
    LiveData<List<Catalog>> getAllLiveData();


    @Query("SELECT * FROM Catalog WHERE uid IN (:userIds)")
    List<Catalog> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Catalog WHERE uid = :uid LIMIT 1")
    Catalog findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Catalog catalog);

    @Update
    void update(Catalog catalog);

    @Delete
    void delete(Catalog catalog);


}
