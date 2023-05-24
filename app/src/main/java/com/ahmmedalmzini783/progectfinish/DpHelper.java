package com.ahmmedalmzini783.progectfinish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmmedalmzini783.progectfinish.classt.Admin;
import com.ahmmedalmzini783.progectfinish.classt.Subject;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper {

    public DpHelper(@Nullable Context context) {
        super(context, "Admin", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Admin.CREATE_TABLE);
        db.execSQL(Subject.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Admin.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Subject.TABLE_NAME);
        onCreate(db);
    }



    //  ******************************* Admin دوال ******************************


    public boolean insertAdmin(String username,String email,String password){
        SQLiteDatabase db1=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Admin.COL_USERNAME,username);
        values.put(Admin.COL_EMAIL,email);
        values.put(Admin.COL_PASSWORD,password);


        long row=db1.insert(Admin.TABLE_NAME,null,values);
        return row>0;

    }


    public boolean updateAdmin(String id,String username,String email,String password){
        SQLiteDatabase dp1=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Admin.COL_USERNAME,username);
        values.put(Admin.COL_EMAIL,email);
        values.put(Admin.COL_PASSWORD,password);

        int rowID=dp1.update(Admin.TABLE_NAME,values,Admin.COL_ID+"=?",new String[]{id});
        return rowID>0;

    }


    public boolean deleteAdmin(String id){
        SQLiteDatabase db1=getWritableDatabase();
        int rowID=db1.delete(Admin.TABLE_NAME,Admin.COL_ID+"=?",new String[]{id});
        return rowID>0;
    }


    public ArrayList<Admin> getAllDataAdmin(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Admin> dataAdmin=new ArrayList<>();

        String query="SELECT * FROM "+Admin.TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(Admin.COL_ID));
                String username=cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_USERNAME));
                String email=cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_EMAIL));
                String password=cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_PASSWORD));

                Admin admin=new Admin(id,username,email,password);
                dataAdmin.add(admin);


            }while (cursor.moveToNext());
            cursor.close();
        }
        return dataAdmin;
    }


    //  ******************************* Subject دوال ******************************


}
