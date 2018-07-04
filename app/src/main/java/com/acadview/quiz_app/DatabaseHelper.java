package com.acadview.quiz_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class DatabaseHelper extends SQLiteOpenHelper {

    // Variable declaration for database.

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "SCORES";
    private static final String COL1 = "Correct_Answers";
    private static final String COL2 = "Incorrect_Answers";
    private static final String COL3 = "Score";
    private static final String COL4 = "Percentage";
    private static final String COL5 = "Total_Questions_Answered";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INT(10), " + COL2 + " INT(10), "
                + COL3 + " VARCHAR(5), " + COL4 + " DECIMAL(3,1), " + COL5 + " INT(10));";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Function to add data to database.

    public void addData(int a, int b, String c, float d, int e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL1, a);
        values.put(COL2, b);
        values.put(COL3, c);
        values.put(COL4, d);
        values.put(COL5, e);

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    /**
     * Returns all the data from database
     * @return
     */

     //Function to receive data from database
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT DISTINCT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}

