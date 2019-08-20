package com.example.whichoneislargerwithfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<PlayerAttributes> items;

    public UserListAdapter(List<PlayerAttributes> items) {
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.row_user_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.playerName.setText(items.get(position).getName());
        viewHolder.playerScore.setText(String.valueOf(items.get(position).getScore()));
        viewHolder.playerLevel.setText(String.valueOf(items.get(position).getLevel()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView playerName;
        public TextView playerScore;
        public TextView playerLevel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.user_name);
            playerScore = itemView.findViewById(R.id.user_score);
            playerLevel = itemView.findViewById(R.id.user_level);
        }
    }
}
