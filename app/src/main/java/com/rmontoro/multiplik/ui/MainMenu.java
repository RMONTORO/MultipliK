package com.rmontoro.multiplik.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmontoro.multiplik.R;
import com.rmontoro.multiplik.utilities.Utils;


public class MainMenu extends Fragment implements View.OnClickListener{

    public MainMenu() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        displayRandomMultiplication((TextView) v.findViewById(R.id.falshcard_txt));

        v.findViewById(R.id.learn_btn).setOnClickListener(this);
        v.findViewById(R.id.play_btn).setOnClickListener(this);
        v.findViewById(R.id.settings_btn).setOnClickListener(this);




        return v;
    }

    private void displayRandomMultiplication(TextView tv){
        int n1, n2, result;
        String message;
        n1 = Utils.getRandomNumber();
        n2 = Utils.getRandomNumber();
        result = n1*n2;
        message = n1 + " X " + n2 + " = " + result;
        tv.setText(message);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.learn_btn){
            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new LearnFragment()).commit();
        }else if(id == R.id.play_btn){

        }else if(id == R.id.settings_btn){

        }
    }
}