package com.example.rovermore.googlebooks;

import java.util.ArrayList;

/**
 * Created by robertomoreno on 21/11/17.
 */

public class Book {


    private String tit;
    private ArrayList<String> author;
    private String year;

    public Book(ArrayList<String> authors, String title, String yy) {


        this.author = authors;
        this.tit = title;
        this.year = yy;
    }

    public String getNameBook (){

        return tit;
    }

    public ArrayList<String> getAuthors(){

        return author;
    }

    public String getYear(){

        return year;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
