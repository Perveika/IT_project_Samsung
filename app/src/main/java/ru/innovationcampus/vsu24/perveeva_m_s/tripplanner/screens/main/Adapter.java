package ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.List;

import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.App;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.R;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.model.Catalog;
import ru.innovationcampus.vsu24.perveeva_m_s.tripplanner.screens.main.details.EditingDishActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.CatalogViewHolder> {


    private SortedList<Catalog> sortedList;

    public Adapter() {
        sortedList = new SortedList<>(Catalog.class, new SortedList.Callback<Catalog>() {
            @Override
            public int compare(Catalog o1, Catalog o2) {
                if (!o2.added && o1.added) {
                    return 1;
                }
                if (o2.added && !o1.added) {
                    return -1;
                }
                return (int)(o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Catalog oldItem, Catalog newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Catalog item1, Catalog item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }




    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatalogViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItem(List<Catalog> catalogs) {
        sortedList.replaceAll(catalogs );
    }

    static class CatalogViewHolder extends RecyclerView.ViewHolder {
        TextView catalogName;
        ImageButton edit;
        ImageButton delete;
        CheckBox add;


        Catalog catalog;
        boolean silentUpdate;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);

            catalogName = itemView.findViewById(R.id.catalog_name);
            edit = itemView.findViewById(R.id.edit_dish);
            delete = itemView.findViewById(R.id.delete_dish);
            add = itemView.findViewById(R.id.added_dish);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditingDishActivity.start((Activity) itemView.getContext(), catalog);
                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getInstance().getDataDao().delete(catalog);
                }
            });


            add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!silentUpdate) {
                        catalog.added = isChecked;
                        App.getInstance().getDataDao().update(catalog);
                    }
                }
            });
        }

        public void bind(Catalog catalog) {
            this.catalog = catalog;

            catalogName.setText(catalog.dishName);

            silentUpdate = true;
            add.setChecked(catalog.added);
            silentUpdate = false;
        }
    }
}
