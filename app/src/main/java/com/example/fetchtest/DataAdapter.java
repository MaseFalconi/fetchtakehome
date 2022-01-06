package com.example.fetchtest;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fetchtest.model.ListData;
import java.util.List;

/*
*
* Adapter class that binds to the RecyclerView to feed the list data
* Data is collected from the ListData class
*
* */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<ListData> dataList;

    public DataAdapter(List<ListData> listData){
        dataList = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row, parent, false);

        return new DataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getIDTextView().setText("ID: " + String.valueOf(dataList.get(position).getId()));
        holder.getListIDTextView()
                .setText("List ID: " + String.valueOf(dataList.get(position).getListId()));
        holder.getNameTextView().setText("Name: " + dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idTextView;
        public TextView listIDTextView;
        public TextView nameTextView;

        public ViewHolder(View view) {
            super(view);

            idTextView = (TextView) view.findViewById(R.id.id_tv);
            listIDTextView = (TextView) view.findViewById(R.id.listID_tv);
            nameTextView = (TextView) view.findViewById(R.id.name_tv);
        }

        public TextView getIDTextView() {
            return idTextView;
        }
        public TextView getListIDTextView() {
            return listIDTextView;
        }
        public TextView getNameTextView() {
            return nameTextView;
        }
    }
}
