package com.example.rovermore.googlebooks;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by robertomoreno on 21/11/17.
 */


public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {


    public static final String URL_BOOKS_REQUEST= "https://www.googleapis.com/books/v1/volumes?";
    private TextView emptyStateView;
    private ProgressBar progressBar;

    //Tag for the log messages */
    public static final String LOG_TAG = BookActivity.class.getSimpleName();
    private EditText userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.books_oncreate_activity);

        //Inflates emptyStateView in order to not apperar in screen until loading has finished
        emptyStateView = (TextView) findViewById(R.id.empty_state);

        //Saves ProgresBar view into an object in order to treat it from java
        progressBar= (ProgressBar) findViewById(R.id.loading_spinner);

        userQuery = (EditText)findViewById(R.id.book_search_bar);

        Button search = (Button)findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks If the user did not write a query Showing a toast in case not
                if(userQuery!=null){


                    progressBar.setVisibility(View.VISIBLE);

                    Log.v("initloader", "The oncreate has runned and loader has started");
                    getLoaderManager().initLoader(0,null,BookActivity.this).forceLoad();


                }else{
                    Toast.makeText(getApplicationContext(), "Introduce a query",
                            Toast.LENGTH_LONG).show();
                }

            }
        });



    }


    private void createUI(final ArrayList<Book> bookArrayList){



        BookAdapter adapter = null;

        adapter = new BookAdapter(this, bookArrayList);

        ListView booksListView = (ListView) findViewById(R.id.list);

        booksListView.setEmptyView(emptyStateView);

        booksListView.setAdapter(adapter);


    }


    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle) {


        final String userSearchText= userQuery.getText().toString();
        Log.v("onCreateLoader","onCreate loader running loader should be created");

        BookLoader loader = new BookLoader(this,userSearchText);


        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> books) {

        Log.v("onLoadFinished","update of the UI should start and create de interface");



        //Makes progerss bar disappear when the loader is loaded
        progressBar.setVisibility(View.GONE);

        createUI(books);


        getLoaderManager().destroyLoader(0);


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {

        Log.v("onLoaderReset","Create a new arraylist");
        createUI(new ArrayList<Book>());



    }

}
