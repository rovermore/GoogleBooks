package com.example.rovermore.googlebooks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by robertomoreno on 21/11/17.
 */

public  class BookLoader extends AsyncTaskLoader {

    String userQuery;

    ArrayList<Book> books = new ArrayList<>();

    public BookLoader(Context context, String str){
        super(context);

        this.userQuery=str;

    }


    /*protected void onStartLoading() {
        forceLoad();
    }*/

    @Override
    public ArrayList<Book> loadInBackground() {



        //This try and catch makes the loader wait for two seconds to star working
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String stringUrl = QueryUtils.createUrlWithQuery(String.valueOf(userQuery));

        URL urlObject = QueryUtils.createURL(stringUrl);

        String jSonResponse ="";
        try {
            jSonResponse = QueryUtils.makeHttpRequest(urlObject);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            books = QueryUtils.extractBooks(jSonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return books;
    }
}
