package com.example.christy0514.homework5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by christy0514 on 2017/12/20.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ContactBook";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Contact";
    private static final String COL_id = "_id";
    private static final String COL_name = "name";
    private static final String COL_phone = "phone";
    private static final String COL_text = "text";

    private static final String TABLE_CREATE =
            "CREATE  TABLE " + TABLE_NAME +"(_id INTEGER PRIMARY KEY NOT NULL , "+
                    //COL_id + " INTEGER , "+
                    COL_name+" TEXT NOT NULL , "+
                    COL_phone+" TEXT , "+
                    COL_text+" TEXT ) ";

    public MyDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        /*"CREATE  TABLE Contact " +
                "(_id INTEGER PRIMARY KEY  NOT NULL , " +
                "id INTEGER , "+
                "name TEXT NOT NULL , " +
                "phone TEXT , " +
                "text TEXT)"*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
    /*public ArrayList<HashMap<String, String>> getAllContact(){
         SQLiteDatabase db = getReadableDatabase();
         String[] columns = {
                 COL_id,COL_name,COL_phone,COL_text
         };
         Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,null);
        ArrayList<HashMap<String,String>> contactList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> contact=new HashMap<>();
                contact.put("name",cursor.getString(1));
                contact.put("phone",cursor.getString(2));
                contactList.add(contact);
            }while(cursor.moveToNext());

        }
         /*while (cursor.moveToNext()){
             int id = cursor.getInt(0);
             String name = cursor.getString(1);
             String phone = cursor.getString(2);
             String text = cursor.getString(3);

             Contact contact = new Contact(id,name,phone,text);
             contactList.add(contact);
         }
         cursor.close();
         return contactList;
     }*/
    public Contact findById(long id){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                "_id",COL_name,COL_phone,COL_text
        };
        String selection = "_id"+"=?;";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        Contact contact = null;
        if(cursor.moveToNext()){
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String text = cursor.getString(3);

            contact = new Contact(id,name,phone,text);
        }
        cursor.close();
        return contact;
    }
    public Contact findByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                "_id",COL_name,COL_phone,COL_text
        };
        String selection = COL_name+"=?;";
        String[] selectionArgs = {String.valueOf(name)};

        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        Contact contact = null;
        if(cursor.moveToNext()){
            long id = cursor.getLong(0);
            name = cursor.getString(1);
            String phone = cursor.getString(2);
            String text = cursor.getString(3);

            contact = new Contact(id,name,phone,text);
        }
        cursor.close();
        return contact;
    }

    public long insert(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_name,contact.getName());
        values.put(COL_phone,contact.getPhone());
        values.put(COL_text,contact.getText());
        return db.insert(TABLE_NAME,null,values);
    }
    public long update(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_name,contact.getName());
        values.put(COL_phone,contact.getPhone());
        values.put(COL_text,contact.getText());
        String whereClause = "_id"+"=?;";
        String[] whereArgs = {Long.toString(contact.getId())};
        return db.update(TABLE_NAME,values,whereClause,whereArgs);
    }
    public long deleteById(long id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "_id"+"=?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME,whereClause,whereArgs);
    }
   /* public long search(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
    }*/
}
