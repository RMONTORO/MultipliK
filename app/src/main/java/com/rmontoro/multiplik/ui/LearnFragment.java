package com.rmontoro.multiplik.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmontoro.multiplik.R;


public class LearnFragment extends Fragment implements View.OnClickListener {

    public LearnFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learn, container, false);

        v.findViewById(R.id.memory_cards_btn).setOnClickListener(this);
        v.findViewById(R.id.tables_btn).setOnClickListener(this);
        v.findViewById(R.id.rush_btn).setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.memory_cards_btn){
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new LearnMemoryFragment()).commit();
        }else if(id == R.id.tables_btn){

        }else if(id == R.id.rush_btn){

        }
    }
}