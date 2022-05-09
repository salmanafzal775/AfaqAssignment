package com.example.androidassignment.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.androidassignment.Model.AddressModel;
import com.example.androidassignment.Model.UserModel;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "addressDb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myAddresses";
    private static final String ID_COL = "id";
    private static final String ADDRESS_COL = "address";
    private static final String DESCRIPTION_COL = "description";
    private static final String IMAGE_COL = "image";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADDRESS_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addImage(String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_COL, image);

        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public void addNewAddress(String address, String description) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADDRESS_COL, address);
        values.put(DESCRIPTION_COL, description);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<AddressModel> readAddresses() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<AddressModel> addressList = new ArrayList<>();

        if (cursorUser.moveToFirst()) {
            do {
                addressList.add(new AddressModel(cursorUser.getString(1),
                        cursorUser.getString(2)));
            } while (cursorUser.moveToNext());
        }
        cursorUser.close();
        return addressList;
    }

    public void updateAddress(String originalAddress, String address, String description
                             ) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ADDRESS_COL, address);
        values.put(DESCRIPTION_COL, description);


        db.update(TABLE_NAME, values, "address=?", new String[]{originalAddress});
        db.close();
    }

    public void deleteAddress(String courseName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "address=?", new String[]{courseName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
