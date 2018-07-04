package com.acadview.quiz_app;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    //Variable declaration.

    private static final String TAG = "ResultActivity";
    private ListView lg;
    DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lg = (ListView) findViewById(R.id.list_item);
        mDb = new DatabaseHelper(this);

        populateListView();
    }
       // Function to populate data into database
    private void populateListView() {
        //get the data and append to a list
        Cursor data = mDb.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){

            String correct = data.getString(data.getColumnIndex("Correct_Answers"));
            String incorrect = data.getString(data.getColumnIndex("Incorrect_Answers"));
            String score = data.getString(data.getColumnIndex("Score"));
            String percent = data.getString(data.getColumnIndex("Percentage"));
            String attempt = data.getString(data.getColumnIndex("Total_Questions_Answered"));
            listData.add("      " + correct + "                      " + incorrect + "         " + score + "         " + percent + "%       " + attempt);
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lg.setAdapter(adapter);
    }
}
