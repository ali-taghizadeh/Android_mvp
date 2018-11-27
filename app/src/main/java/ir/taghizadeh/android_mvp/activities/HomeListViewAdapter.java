package ir.taghizadeh.android_mvp.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.taghizadeh.android_mvp.R;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.utils.OnHomeItemClickListener;


public class HomeListViewAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    public List<HomeDTO> homeDTOS;
    private OnHomeItemClickListener listener;

    public HomeListViewAdapter(List<HomeDTO> homeDTOS, OnHomeItemClickListener listener) {
        this.homeDTOS = homeDTOS;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        final HomeListViewHolder viewHolder = new HomeListViewHolder(view);
        view.setOnClickListener(v -> listener.onItemClick(v, viewHolder.getAdapterPosition()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int index) {
        HomeDTO homeDTO = homeDTOS.get(index);
        holder.configureWith(homeDTO);
    }

    @Override
    public int getItemCount() {
        return homeDTOS.size();
    }
}
