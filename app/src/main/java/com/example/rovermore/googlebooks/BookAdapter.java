package com.example.rovermore.googlebooks;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robertomoreno on 21/11/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {


    public BookAdapter(@NonNull Activity context, ArrayList<Book> Book) {
        super(context,0,Book);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_id, parent, false);
        }

        Book currentArrayBook = getItem(position);

        TextView bookName = (TextView) listItemView.findViewById(R.id.book_name);
        bookName.setText(currentArrayBook.getNameBook());

        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.book_author);

        StringBuilder authors = new StringBuilder();

        for(int i=0;i<currentArrayBook.getAuthors().size();i++){

            String autor = currentArrayBook.getAuthors().get(i);

            authors.append(autor);
            authors.append(", ");
        }

        bookAuthor.setText(authors);


        TextView bookYear = (TextView)listItemView.findViewById(R.id.year);

        bookYear.setText(currentArrayBook.getYear().toString());

        return listItemView;
    }
}
