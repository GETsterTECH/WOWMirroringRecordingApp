package com.wowmirroringrecordingapp.sqlite.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wowmirroringrecordingapp.sqlite.data.DatabaseManager;

public class SignUpRepo {
    SignUpModel customerModel;

    public SignUpRepo() {
        customerModel = new SignUpModel();
    }

    public static String createTable() {
        return "CREATE TABLE " + SignUpModel.TABLE + "("
                + SignUpModel.KEY_cus_id + " TEXT ,"
                + SignUpModel.KEY_parish_name + " TEXT ,"
                + SignUpModel.KEY_cus_name + " TEXT ,"
                + SignUpModel.KEY_cus_psw + " TEXT ,"
                + SignUpModel.KEY_cus_mobile + " TEXT ,"
                + SignUpModel.KEY_trans_id + " TEXT ,"
                + SignUpModel.KEY_cus_type + " TEXT ,"
                + SignUpModel.KEY_date + " TEXT )";
    }


    public int insert(SignUpModel product) {
        int courseId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(SignUpModel.KEY_cus_id, product.getCus_id());
        values.put(SignUpModel.KEY_parish_name, product.getCus_name());
        values.put(SignUpModel.KEY_cus_name, product.getCus_name());
        values.put(SignUpModel.KEY_cus_psw, product.getCus_psw());
        values.put(SignUpModel.KEY_cus_mobile, product.getCus_mobile());
        values.put(SignUpModel.KEY_cus_type, product.getCus_mobile());
        values.put(SignUpModel.KEY_trans_id, product.getCus_mobile());
        values.put(SignUpModel.KEY_date, product.getCus_mobile());

        // Inserting Row
        courseId = (int) db.insert(SignUpModel.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();

        return courseId;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor res = db.rawQuery("select * from " + SignUpModel.TABLE, null);
        return res;
    }
}
