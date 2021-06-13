package com.example.basic_banking_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private  Context context;
    private static final String DATABASE_NAME = "BankDetails.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_Details";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "user_name";
    private static final String COLUMN_BALANCE = "user_balance";
    private static final String COLUMN_BRANCH = "user_branch";
    private static final String COLUMN_ACNO = "user_acno";

    private static final String TABLE_NAME2 = "trans_Details";
    private static final String COLUMN_ID2 = "_id2";
    private static final String COLUMN_TITLE2 = "user_name2";
    private static final String COLUMN_BALANCE2 = "user_balance2";
    private static final String COLUMN_STATUS2 = "status";

    private static final String TABLE_NAME3 = "My_Detailss";
    private static final String COLUMN_ID3 = "_id3";
    private static final String COLUMN_TITLE3 = "user_name3";
    private static final String COLUMN_BALANCE3 = "user_balance3";
    private static final String COLUMN_ACNO3 = "user_acno3";
    private static final String COLUMN_BRANCH3 = "user_branch3";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE + " TEXT, "+
                COLUMN_ACNO + " LONG, " +
                COLUMN_BRANCH + " TEXT, "+
                COLUMN_BALANCE+" INTEGER);";
        db.execSQL(query);

        String query2 = "CREATE TABLE "+ TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE2 + " TEXT, "+
                COLUMN_STATUS2 + " TEXT, "+
                COLUMN_BALANCE2+" INTEGER);";
        db.execSQL(query2);

        String query3 = "CREATE TABLE "+ TABLE_NAME3 +
                " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_TITLE3 + " TEXT, "+
                COLUMN_ACNO3 + " LONG, "+
                COLUMN_BRANCH3 + " TEXT, "+
                COLUMN_BALANCE3+" INTEGER);";
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);

        onCreate(db);
    }

    void addAccount(String title,Long acno,String branch,int balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE3,title);
        cv.put(COLUMN_ACNO3,acno);
        cv.put(COLUMN_BRANCH3,branch);
        cv.put(COLUMN_BALANCE3,balance);

        long result = db.insert(TABLE_NAME3,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Account Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addTransation(String title,String status,int balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE2,title);
        cv.put(COLUMN_STATUS2,status);
        cv.put(COLUMN_BALANCE2,balance);

        long result = db.insert(TABLE_NAME2,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(context, " ", Toast.LENGTH_SHORT).show();
//        }
    }

    void addBook(String title,Long acno,String branch,int balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_ACNO,acno);
        cv.put(COLUMN_BRANCH,branch);
        cv.put(COLUMN_BALANCE,balance);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "User Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readaccountData(){
        String query = "SELECT * FROM "+TABLE_NAME3;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readTransAData(){
        String query = "SELECT * FROM "+TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readBalance(){
        String query = "SELECT * FROM "+TABLE_NAME3;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor curs = null;
        if(db != null){
            curs = db.rawQuery(query,null);
        }
        return curs;
    }
    Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    long updateData(String title,int balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BALANCE,balance);

        long result = db.update(TABLE_NAME, cv ,"user_name=?", new String[]{title});
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Transaction  Successful!", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    void updateMyDataBalance(String name, int balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BALANCE3,balance);
        String row = "0";

        long result = db.update(TABLE_NAME3, cv ,"user_name3=?",new String[]{name});
        if(result == -1) {
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }
//        }else{
//            Toast.makeText(context, " ", Toast.LENGTH_SHORT).show();
//        }
    }
}
