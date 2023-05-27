package com.ahmmedalmzini783.progectfinish;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ahmmedalmzini783.progectfinish.classt.Admin;
import com.ahmmedalmzini783.progectfinish.classt.Presence;
import com.ahmmedalmzini783.progectfinish.classt.Students;
import com.ahmmedalmzini783.progectfinish.classt.Subject;
import com.ahmmedalmzini783.progectfinish.classt.Subject_Student;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper {

    public DpHelper(@Nullable Context context) {
        super(context, "Admin", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Admin.CREATE_TABLE);
        db.execSQL(Subject.CREATE_TABLE);
        db.execSQL(Students.CREATE_TABLE);
        db.execSQL(Subject_Student.CREATE_TABLE);
        db.execSQL(Presence.CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Admin.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Subject.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Students.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Subject_Student.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+Presence.TABLE_NAME);
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


    public String[] loginData(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {Admin.COL_USERNAME, Admin.COL_EMAIL};
        String selection = Admin.COL_USERNAME + " =? AND " + Admin.COL_PASSWORD + " =?";
        String[] selectionArgs = {username, password};
        String limit = "1";

        Cursor cursor = db.query(Admin.TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean isMatched = cursor.moveToFirst();

        String[] userData = new String[2]; // مصفوفة لتخزين بيانات المستخدم

        if (isMatched) {
            userData[0] = cursor.getString(cursor.getColumnIndex(Admin.COL_USERNAME)); // استخراج اسم المستخدم
            userData[1] = cursor.getString(cursor.getColumnIndex(Admin.COL_EMAIL)); // استخراج البريد الإلكتروني
        }

        cursor.close();
        return userData;
    }





    public boolean login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {Admin.COL_USERNAME};
        String selection = Admin.COL_USERNAME + " =? AND " + Admin.COL_PASSWORD + " =?";
        String[] selectionArgs = {username, password};
        String limit = "1";

        Cursor cursor = db.query(Admin.TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean isMatched = cursor.moveToFirst();
        cursor.close();
        return isMatched;
    }

    //  ******************************* Subject دوال ******************************

    public boolean insertSubject(String subjectName){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Subject.COL_SUBJECT_NAME,subjectName);
        long row=db.insert(Subject.TABLE_NAME,null,values);
        return row>0;
    }

    public boolean updateSubject(String id,String subjectName){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Subject.COL_SUBJECT_NAME,subjectName);
        long rowID=db.update(Subject.TABLE_NAME,values,Subject.COL_ID+"=?",new String[]{id});
        return rowID>0;
    }

    public boolean deleteSubject(String id){
        SQLiteDatabase db=getWritableDatabase();
        int rowID=db.delete(Subject.TABLE_NAME,Subject.COL_ID+"=?",new String[]{id});
        return rowID>0;
    }

    public ArrayList<Subject> getAllDataSubject(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Subject> data=new ArrayList<>();

        String query="SELECT * FROM "+Subject.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject.COL_ID));
                String subjectName=cursor.getString(cursor.getColumnIndexOrThrow(Subject.COL_SUBJECT_NAME));


                Subject subject=new Subject(subjectName,id);
                data.add(subject);

            }while (cursor.moveToNext());
            cursor.close();
        }


        return data;
    }



    //  ******************************* Student دوال ******************************

    public boolean insertStudent(String firstName,String lastName,int age){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Students.COL_FIRST_NAME,firstName);
        values.put(Students.COL_LAST_NAME,lastName);
        values.put(Students.COL_AGE,age);
        long row=db.insert(Students.TABLE_NAME,null,values);
        return row>0;
    }

    public boolean updateStudents(String id,String firstName,String lastName,int age ){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Students.COL_FIRST_NAME,firstName);
        values.put(Students.COL_LAST_NAME,lastName);
        values.put(Students.COL_AGE,age);

        long rowID=db.update(Students.TABLE_NAME,values,Students.COL_ID+"=?",new String[]{id});
        return rowID>0;

    }

    public boolean deleteStudents(String id){
        SQLiteDatabase db=getWritableDatabase();
        int rowID=db.delete(Students.TABLE_NAME,Students.COL_ID+"?=",new String[]{id});
        return rowID>0;
    }

    public ArrayList<Students> getAllDataStudents(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Students> data=new ArrayList<>();

        String query="SELECT * FROM "+Students.TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                String firstName=cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                String lastName=cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                int age=cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                Students students=new Students(id,firstName,lastName,age);
                data.add(students);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }


    //  ******************************* Subject_Student دوال ******************************

    public boolean insertStudent_Subject(int studentId, int subjectId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject_Student.COL_STUDENTS_ID, studentId);
        values.put(Subject_Student.COL_SUBJECT_ID, subjectId);

        long row = db.insert(Subject_Student.TABLE_NAME, null, values);
        return row > 0;
    }

    public boolean updateStudents_Subject(String id, int studentId, int subjectId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject_Student.COL_STUDENTS_ID, studentId);
        values.put(Subject_Student.COL_SUBJECT_ID, subjectId);

        long rowID = db.update(Subject_Student.TABLE_NAME, values, Subject_Student.COL_ID + "=?", new String[]{id});
        return rowID > 0;
    }

    public boolean deleteStudents_Subject(String id){
        SQLiteDatabase db=getWritableDatabase();
        int rowID=db.delete(Subject_Student.TABLE_NAME,Subject_Student.COL_ID+"?=",new String[]{id});
        return rowID>0;
    }

    public ArrayList<Subject_Student> dataStudents_Subject(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Subject_Student> data=new ArrayList<>();

        String query="SELECT * FROM "+Subject_Student.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_ID));
                int student_id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_STUDENTS_ID));
                int subject_id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_SUBJECT_ID));

                Subject_Student subject_Student=new Subject_Student(id,student_id,subject_id);
                data.add(subject_Student);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }



    //  ******************************* presence دوال ******************************

    public boolean insertPresence(String month, int day) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Presence.COL_MONTH, month);
        values.put(Presence.COL_DAY, day);

        long row = db.insert(Presence.TABLE_NAME, null, values);
        return row > 0;
    }

    public boolean updatePresence(String id,String month, int day) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Presence.COL_MONTH, month);
        values.put(Presence.COL_DAY, day);

        long rowID = db.update(Presence.TABLE_NAME, values, Presence.COL_ID + "=?", new String[]{id});
        return rowID > 0;
    }

    public boolean deletePresence(String id){
        SQLiteDatabase db=getWritableDatabase();
        int rowID=db.delete(Presence.TABLE_NAME,Presence.COL_ID+"?=",new String[]{id});
        return rowID>0;
    }

    public ArrayList<Presence> dataPresence(){
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Presence> data=new ArrayList<>();

        String query="SELECT * FROM "+Presence.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(Presence.COL_ID));
                String month=cursor.getString(cursor.getColumnIndexOrThrow(Presence.COL_MONTH));
                int day=cursor.getInt(cursor.getColumnIndexOrThrow(Presence.COL_DAY));
                int student_id=cursor.getInt(cursor.getColumnIndexOrThrow(Presence.COL_STUDENT_ID));
                int subject_id=cursor.getInt(cursor.getColumnIndexOrThrow(Presence.COL_SUBJECT_ID));

                Presence presence=new Presence(id,month,day,student_id,subject_id);
                data.add(presence);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }

}
