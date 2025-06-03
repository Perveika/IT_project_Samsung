package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.R;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main.details.EditingDishActivity;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditingDishActivity.start(MainActivity.this, null);
            }
        });


        MainNewModel mainNewModel = ViewModelProviders.of(this).get(MainNewModel.class);

        mainNewModel.getCatalogLiveData().observe(this, new Observer<List<Catalog>>() {
            @Override
            public void onChanged(List<Catalog> catalogs) {
                adapter.setItem(catalogs);
            }
        });
    }



}