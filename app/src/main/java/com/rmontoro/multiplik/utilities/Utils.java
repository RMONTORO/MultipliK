package com.rmontoro.multiplik.utilities;

import java.util.Random;

public final class Utils {

    public static int getRandomNumber(){
        Random r = new Random();
        return r.nextInt(11);
    }



}
