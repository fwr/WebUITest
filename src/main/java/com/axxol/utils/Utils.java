package com.axxol.utils;

public class Utils {
    public static void sleep(int s){
        try{
            Thread.sleep(s*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
