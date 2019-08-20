package com.example.whichoneislargerwithfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BestScoreFragment extends Fragment {

    RecyclerView playerList;
    UserListAdapter userListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.best_score_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        setRecyclerLayout();


    }

    private void findView(View view) {
        playerList = view.findViewById(R.id.user_list_recycler_view);
    }

    private void setRecyclerLayout(){
        PlayerRankedList rankedList = MyPreferencesManager.setInstance(getActivity()).getPlayerListManager();
        userListAdapter = new UserListAdapter(rankedList.getPlayerRankedList());
        playerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        playerList.setAdapter(userListAdapter);
        for (PlayerAttributes player : rankedList.getPlayerRankedList()){
            Log.d("Tag", "player list" + player.getName() + " "+ player.getScore()+ " " + player.getLevel());
        }



    }


    }


