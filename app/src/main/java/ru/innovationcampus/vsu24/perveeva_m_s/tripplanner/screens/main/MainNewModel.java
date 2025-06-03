package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.App;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;

public class MainNewModel extends ViewModel {
    private LiveData<List<Catalog>> catalogLiveData = App.getInstance().getDataDao().getAllLiveData();

    public LiveData<List<Catalog>> getCatalogLiveData() {
        return catalogLiveData;
    }
}
