package com.infinix.crudapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {


    public static final String COL_1 = "name";
    public static final String COL_2 = "address";
    public static final String COL_3 = "contact";
    public static final String COL_4 = "dob";
    private final String tableName = "tbl_user_record";

    public DBHelper(@Nullable Context context) {
           super(context, "userRecord.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE tbl_user_record(" +
                        "id integer PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT, contact TEXT, " +
                        "address TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if exists tbl_user_record");
        onCreate(db);
    }

 
    public Boolean insertData(String name, String contact, String address, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, address);
        contentValues.put(COL_3, contact);
        contentValues.put(COL_4, dob);

        long result = 0;
        try {
            result = db.insert(tableName, null, contentValues);
        } catch (SQLException e) {
            e.printStackTrace(); // handle exception
        } finally {
            db.close(); // close the database connection
        }
        return result != -1; // return true if result is not -1

    }


    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + tableName + " GROUP BY name"; 
    
        return db.rawQuery(query, null);
    }


    public Boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(tableName, COL_1 + " = ?", new String[]{name});
        db.close();
        return result > 0;
    }

  
    public Boolean updateData(String name, String address, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase(); 
     
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, address);
        contentValues.put(COL_3, contact);
        contentValues.put(COL_4, dob);

        int result = db.update(tableName, contentValues, COL_1 + " = ?", new String[]{name});
        db.close();
        return result > 0;
    }
}
