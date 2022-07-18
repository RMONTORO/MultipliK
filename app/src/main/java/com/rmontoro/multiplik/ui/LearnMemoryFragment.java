package com.rmontoro.multiplik.ui;

import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rmontoro.multiplik.MainActivity;
import com.rmontoro.multiplik.R;
import com.rmontoro.multiplik.data.multiplicationTable;
import com.rmontoro.multiplik.utilities.Utils;


public class LearnMemoryFragment extends Fragment implements View.OnClickListener {
    private int classHash = LearnMemoryFragment.class.hashCode();
    private int selectedNumber = 0;
    private GridLayout grid;
    private TextView titleTV;
    private ImageView flashCardimg;
    private boolean firstCardSelected = false;
    private boolean secondCardSelected = false;
    private int card1Id = -1;
    private int card2Id = -1;
    final String OPTION = "OPTION";
    final String CARD = "CARD";

    public LearnMemoryFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_learn_memory, container, false);

        //Generate available option (0-10 and random)
        grid = (GridLayout) v.findViewById(R.id.memory_grid);
        for (int i = 0; i <= 10; i++){
            addTableOption(grid, i);
        }
        addTableOption(grid, 11);


        titleTV = v.findViewById(R.id.title_text);
        flashCardimg = v.findViewById(R.id.flashcard_img);




        return v;
    }

    private void addTableOption(GridLayout gridLayout, int id){
        Button option = new Button(this.getContext());
        String txt = (id == 11 ? "?" : String.valueOf(id));
        option.setText(txt);
        option.setTextSize(50);
        option.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        option.setTextColor(getResources().getColor(R.color.white));
        option.setId(classHash + id);
        option.setTag(OPTION);
        option.setOnClickListener(this);
        gridLayout.addView(option);
    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        int id = view.getId();

        if (tag.compareToIgnoreCase(OPTION) == 0){
            int number = id - classHash;
            selectedNumber = (number == 11) ? Utils.getRandomNumber() : number;
            String titleTxt = getResources().getString(R.string.memorize_table_of) + " " + String.valueOf(selectedNumber);
            titleTV.setText(titleTxt);
            grid.removeAllViews();
            startMemoryCardGame();
        }else {
            if (!firstCardSelected){
                firstCardSelected = true;
                card1Id = id;
                showCard(id);
            }else {
                card2Id = id;
                compareCards();
                card1Id = -1;
                card2Id = -1;
                firstCardSelected = false;
            }
        }
    }

    private void showCard(int id){
        Button card = grid.findViewById(id);
        card.setTextColor(getResources().getColor(R.color.white));
    }

    private void hideCard(int id){

    }

    private void compareCards(){
        if (card1Id == -1 || card2Id == -1){
            return;
        }



    }

    private void startMemoryCardGame(){
        multiplicationTable table = new multiplicationTable(selectedNumber);
        flashCardimg.setVisibility(View.GONE);
        titleTV.setVisibility(View.GONE);
        grid.setColumnCount(3);

        for (int i = 0; i < table.getPairs().size(); i++){
            multiplicationTable.Pair pair = table.getPairs().get(i);
            addPairOfCards(grid, pair, i);
        }
    }

    private void addPairOfCards(GridLayout gridLayout, multiplicationTable.Pair pair, int id){
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        int cardWidth = activity.dpToPx(120);
        int cardHeight= activity.dpToPx(80);
        int margins = activity.dpToPx(5);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cardWidth, cardHeight);
        layoutParams.setMargins(margins, margins, margins, margins);

        Button card = new Button(this.getContext());
        card.setLayoutParams(layoutParams);
        card.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.card_border, null));
        card.setTextColor(getResources().getColor(R.color.transparent));
        card.setTag(String.valueOf(pair.getId()));
        card.setOnClickListener(this);
        card.setTextSize(40);

        Button card2 = new Button(this.getContext());
        card2.setLayoutParams(layoutParams);
        card2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.card_border, null));
        card2.setTextColor(getResources().getColor(R.color.transparent));
        card2.setTag(String.valueOf(pair.getId()));
        card2.setOnClickListener(this);
        card2.setTextSize(40);


        String question = pair.getQuestion();
        String answer = pair.getAnswer();
        card.setText(question);
        card2.setText(answer);
        card.setId(classHash + id);
        card2.setId(classHash + id + 100);

        gridLayout.addView(card);
        gridLayout.addView(card2);
    }
}
