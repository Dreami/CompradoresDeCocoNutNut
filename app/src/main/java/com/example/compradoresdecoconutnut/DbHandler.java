package com.example.compradoresdecoconutnut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coconutBuyers.db";
    private static final String TABLE_NAME = "coconuttable";

    public DbHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "address TEXT," +
                        "phone TEXT," +
                        "email TEXT," +
                        "coconuts INTEGER" +
                        ")";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void addClient(Client cliente) {
        ContentValues values = new ContentValues();
        values.put("name", cliente.get_name());
        values.put("address", cliente.get_address());
        values.put("phone", cliente.get_phone());
        values.put("email", cliente.get_email());
        values.put("coconuts", cliente.get_coconuts());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }

    public Client findClientByName(String name) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE name = \"" + name + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Client client = new Client();
        if(cursor.moveToFirst()) {
            client.set_id(Integer.parseInt(cursor.getString(0)));
            client.set_name(cursor.getString(1));
            client.set_address(cursor.getString(2));
            client.set_phone(cursor.getString(3));
            client.set_email(cursor.getString(4));
            client.set_coconuts(Integer.parseInt(cursor.getString(5)));
            cursor.close();
            sqLiteDatabase.close();
            return client;
        }
        sqLiteDatabase.close();
        return null;
    }

    public Client findClientById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE _id = \"" + id + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Client client = new Client();
        if(cursor.moveToFirst()) {
            client.set_id(Integer.parseInt(cursor.getString(0)));
            client.set_name(cursor.getString(1));
            client.set_address(cursor.getString(2));
            client.set_phone(cursor.getString(3));
            client.set_email(cursor.getString(4));
            client.set_coconuts(Integer.parseInt(cursor.getString(5)));
            cursor.close();
            sqLiteDatabase.close();
            return client;
        }
        sqLiteDatabase.close();
        return null;
    }

    public boolean deleteClient(String name) {
        String deleteQuery = "DELETE FROM " + TABLE_NAME +
                " WHERE name = \"" + name + "\"";
        if(findClientByName(name) == null)
            return false;
        else {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL(deleteQuery);
            sqLiteDatabase.close();
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
