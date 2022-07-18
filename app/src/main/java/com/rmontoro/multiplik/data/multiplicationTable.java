package com.rmontoro.multiplik.data;

import java.util.ArrayList;
import java.util.List;

public class multiplicationTable {
    private int tableNumber;
    private List<Pair> pairs;

    public int getTableNumber() {
        return tableNumber;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public multiplicationTable(int tableNumber){
        this.tableNumber = tableNumber;
        this.pairs = buildPairs();
    }

    private List<Pair> buildPairs(){
        List<Pair> newPairsList = new ArrayList<>();

        for (int i = 0; i<=10; i++){
            String question = tableNumber + " X " + i;
            String answer = String.valueOf(tableNumber*i);
            newPairsList.add(new Pair(question, answer, i));
        }

        return newPairsList;
    }


    public class Pair{
        public Pair(String question, String answer, int id){
            this.question = question;
            this.answer = answer;
            this.id = id;
        }
        int id;

        public int getId() {
            return id;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        String question;
        String answer;
    }
}
