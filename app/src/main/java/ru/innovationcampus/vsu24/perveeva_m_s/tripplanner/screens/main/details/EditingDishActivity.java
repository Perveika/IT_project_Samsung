package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.App;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.R;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;

public class EditingDishActivity extends AppCompatActivity {
    private static final String EXTRA_DISH = "EditingDishActivity.EXTRA_DISH";
    private Catalog catalog;
    private EditText editDishName;
    private EditText editDishDetails;

    public static void start(Activity coller, Catalog catalog) {
        Intent intent = new Intent(coller, EditingDishActivity.class);
        if (catalog != null) {
            intent.putExtra(EXTRA_DISH, catalog);
        }
        coller.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editing_dish);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.new_dish));

        editDishName = findViewById(R.id.add_dish_name_et);
        editDishDetails = findViewById(R.id.add_dish_details_et);

        if (getIntent().hasExtra(EXTRA_DISH)) {
            catalog = getIntent().getParcelableExtra(EXTRA_DISH);
            editDishName.setText(catalog.dishName);
            editDishDetails.setText(catalog.dishDetails);
        } else {
            catalog = new Catalog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_save) {
            if (editDishName.getText().length() > 0) {
                catalog.dishName = editDishName.getText().toString();
                catalog.dishDetails = editDishDetails.getText().toString();
                catalog.added = false;
                catalog.timestamp = System.currentTimeMillis();
                if (getIntent().hasExtra(EXTRA_DISH)) {
                    App.getInstance().getDataDao().update(catalog);
                } else {
                    App.getInstance().getDataDao().insert(catalog);
                }
                finish();
            } else {}
        }

        return super.onOptionsItemSelected(item);
    }
}

//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                break;
//            case R.id.action_save:
//                if (editDishName.getText().length() > 0) {
//                    catalog.dishName = editDishName.getText().toString();
//                    catalog.dishDetails = editDishDetails.getText().toString();
//                    catalog.added = false;
//                    catalog.timestamp = System.currentTimeMillis();
//                    if (getIntent().hasExtra(EXTRA_DISH)) {
//                        App.getInstance().getDataDao().update(catalog);
//                    } else {
//                        App.getInstance().getDataDao().insert(catalog);
//                    }
//                    finish();
//                }
//                break;
//        }