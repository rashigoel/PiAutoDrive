package com.example.hp.Adapter;

/**
 * Created by Rashi on 09-07-2016.
 */
public class Data {
    private static String description, date;

    public Data(String description, String date){
        this.description=description;
        this.date=date;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
