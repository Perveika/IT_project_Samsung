package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Catalog implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "dishName")
    public String dishName;
    @ColumnInfo(name = "dishDetails")
    public String dishDetails;
    @ColumnInfo(name = "added")
    public boolean added;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    public Catalog() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return uid == catalog.uid && added == catalog.added && timestamp == catalog.timestamp && Objects.equals(dishName, catalog.dishName) && Objects.equals(dishDetails, catalog.dishDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, dishName, dishDetails, added, timestamp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
