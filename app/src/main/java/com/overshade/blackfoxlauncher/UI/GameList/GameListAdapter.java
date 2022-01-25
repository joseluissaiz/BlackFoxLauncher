package com.overshade.blackfoxlauncher.UI.GameList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.overshade.blackfoxlauncher.R;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private GameListOptions[] data;
    private LayoutInflater inflater;
    private GameListAdapter.ItemClickListener mClickListener;

    public GameListAdapter(Context context, GameListOptions[] data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_list_row, parent, false);
        return new GameListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameListAdapter.ViewHolder holder, int position) {
        String name = data[position].getName();
        String description = data[position].getDescription();
        holder.name.setText(name);
        holder.description.setText(description);
        holder.icon.setImageResource(data[position].getIconResource());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView  description;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public GameListOptions getItem(int id) {
        return data[id];
    }

    public void setClickListener(GameListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}