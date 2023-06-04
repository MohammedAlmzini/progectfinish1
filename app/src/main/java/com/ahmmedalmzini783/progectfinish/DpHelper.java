package com.ahmmedalmzini783.progectfinish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.models.Admin;
import com.ahmmedalmzini783.progectfinish.models.Presence;
import com.ahmmedalmzini783.progectfinish.models.Students;
import com.ahmmedalmzini783.progectfinish.models.Subject;
import com.ahmmedalmzini783.progectfinish.models.Subject_Student;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DpHelper extends SQLiteOpenHelper {
    public static final String COL_ATTENDANCE_RATE = "attendance_rate";

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
        db.execSQL("DROP TABLE IF EXISTS " + Subject.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Students.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Subject_Student.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Presence.TABLE_NAME);
        onCreate(db);
    }


    //  ******************************* Admin دوال ******************************


    public boolean insertAdmin(String username, String email, String password) {
        SQLiteDatabase db1 = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Admin.COL_USERNAME, username);
        values.put(Admin.COL_EMAIL, email);
        values.put(Admin.COL_PASSWORD, password);


        long row = db1.insert(Admin.TABLE_NAME, null, values);
        return row > 0;

    }


    public boolean updateAdmin(String id, String username, String email, String password) {
        SQLiteDatabase dp1 = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Admin.COL_USERNAME, username);
        values.put(Admin.COL_EMAIL, email);
        values.put(Admin.COL_PASSWORD, password);

        int rowID = dp1.update(Admin.TABLE_NAME, values, Admin.COL_ID + "=?", new String[]{id});
        return rowID > 0;

    }


    public boolean deleteAdmin(String id) {
        SQLiteDatabase db1 = getWritableDatabase();
        int rowID = db1.delete(Admin.TABLE_NAME, Admin.COL_ID + "=?", new String[]{id});
        return rowID > 0;
    }


    public ArrayList<Admin> getAllDataAdmin() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Admin> dataAdmin = new ArrayList<>();

        String query = "SELECT * FROM " + Admin.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Admin.COL_ID));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_USERNAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_EMAIL));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(Admin.COL_PASSWORD));

                Admin admin = new Admin(id, username, email, password);
                dataAdmin.add(admin);


            } while (cursor.moveToNext());
            cursor.close();
        }
        return dataAdmin;
    }
    //  ******************************* Subject دوال ******************************

    public boolean insertSubject(String subjectName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject.COL_SUBJECT_NAME, subjectName);
        long row = db.insert(Subject.TABLE_NAME, null, values);
        return row > 0;
    }

    public boolean updateSubject(String id, String subjectName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject.COL_SUBJECT_NAME, subjectName);
        long rowID = db.update(Subject.TABLE_NAME, values, Subject.COL_ID + "=?", new String[]{id});
        return rowID > 0;
    }

    public boolean deleteSubject(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(Subject.TABLE_NAME, Subject.COL_ID + "=?", new String[]{id});
        db.delete(Subject_Student.TABLE_NAME, Subject_Student.COL_SUBJECT_ID + "=?", new String[]{id});
        return rowID > 0;
    }

    public ArrayList<Subject> getAllDataSubject() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Subject> data = new ArrayList<>();

        String query = "SELECT * FROM " + Subject.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Subject.COL_ID));
                String subjectName = cursor.getString(cursor.getColumnIndexOrThrow(Subject.COL_SUBJECT_NAME));


                Subject subject = new Subject(subjectName, id);
                data.add(subject);

            } while (cursor.moveToNext());
            cursor.close();
        }


        return data;
    }


    //  ******************************* Student دوال ******************************

    public boolean insertStudent(String firstName, String lastName, int age) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_AGE, age);
        long row = db.insert(Students.TABLE_NAME, null, values);
        return row > 0;
    }

    public boolean insertStudent(String firstName, String lastName, int age, ArrayList<Subject> subjects, String parthDay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_PARTH_DAY, parthDay);
        values.put(Students.COL_AGE, age);
        long rowId = db.insert(Students.TABLE_NAME, null, values);

        if (rowId > -1) {
            // تم إدراج الطالب بنجاح
            int studentId = (int) rowId;
            if (subjects != null && !subjects.isEmpty()) {
                for (Subject subject : subjects) {
                    ContentValues subjectValues = new ContentValues();
                    subjectValues.put(Subject_Student.COL_STUDENT_ID, studentId);
                    subjectValues.put(Subject_Student.COL_SUBJECT_ID, subject.getId());
                    db.insert(Subject_Student.TABLE_NAME, null, subjectValues);
                }
            }
            return true;
        } else {
            return false;
        }
    }


    public boolean updateStudents(String id, String firstName, String lastName, int age) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_AGE, age);

        long rowID = db.update(Students.TABLE_NAME, values, Students.COL_ID + "=?", new String[]{id});
        return rowID > 0;

    }


    public boolean updateStudent(int studentId, String firstName, String lastName, int age, ArrayList<Subject> subjects, String parthDay) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Students.COL_FIRST_NAME, firstName);
        values.put(Students.COL_LAST_NAME, lastName);
        values.put(Students.COL_PARTH_DAY, parthDay);
        values.put(Students.COL_AGE, age);

        String whereClause = Students.COL_ID + " = ?";
        String[] whereArgs = {String.valueOf(studentId)};

        int rowsAffected = db.update(Students.TABLE_NAME, values, whereClause, whereArgs);

        if (rowsAffected > 0) {

            // حذف جميع المواد المرتبطة بالطالب من جدول Subject_Student
            String deleteWhereClause = Subject_Student.COL_STUDENT_ID + " = ?";
            String[] deleteWhereArgs = {String.valueOf(studentId)};
            db.delete(Subject_Student.TABLE_NAME, deleteWhereClause, deleteWhereArgs);

            // إعادة إدراج المواد المحدثة في جدول Subject_Student
            if (subjects != null && !subjects.isEmpty()) {
                for (Subject subject : subjects) {
                    ContentValues subjectValues = new ContentValues();
                    subjectValues.put(Subject_Student.COL_STUDENT_ID, studentId);
                    subjectValues.put(Subject_Student.COL_SUBJECT_ID, subject.getId());
                    db.insert(Subject_Student.TABLE_NAME, null, subjectValues);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStudents(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(Students.TABLE_NAME, Students.COL_ID + "=?", new String[]{id});
        db.delete(Subject_Student.TABLE_NAME, Subject_Student.COL_STUDENT_ID + "=?", new String[]{id});
        return rowID > 0;
    }


    public ArrayList<Students> getAllDataStudents() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Students> data = new ArrayList<>();

        String query = "SELECT * FROM " + Students.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                Students students = new Students(id, firstName, lastName, age, parthDay);
                data.add(students);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }


//    public ArrayList<Students> getAllDataStudentsDate(String sortOrder) {
//        SQLiteDatabase db = getReadableDatabase();
//        ArrayList<Students> data = new ArrayList<>();
//
//        String query = "SELECT * FROM " + Students.TABLE_NAME + " ORDER BY " + Students.COL_DATE_ADDED + " " + sortOrder;
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
//                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
//                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
//                String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
//                int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));
//
//                Students students = new Students(id, firstName, lastName, age, parthDay);
//                data.add(students);
//
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//        return data;
//    }

    public ArrayList<Students> getAllDataStudentsFarezeName(String sortOrder) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Students> data = new ArrayList<>();

        String query = "SELECT * FROM " + Students.TABLE_NAME + " ORDER BY " + Students.COL_FIRST_NAME + " " + sortOrder;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                Students students = new Students(id, firstName, lastName, age, parthDay);
                data.add(students);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }



    public ArrayList<Students> getAllStudentsByName(String nameText) {
        ArrayList<Students> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Students.TABLE_NAME + " WHERE " + Students.COL_FIRST_NAME + " LIKE '%" + nameText + "%' ORDER BY " + Students.COL_FIRST_NAME + " ASC";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                    String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                    String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                    String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
                    int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                    Students student = new Students(id, firstName, lastName, age, parthDay);
                    data.add(student);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return data;
        }



    public Students getStudentById(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String selection = Students.COL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                Students.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        Students student = null;
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
            String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

            student = new Students(id, firstName, lastName, age, parthDay);
        }

        cursor.close();
        return student;
    }



    public ArrayList<Subject> getSubjectsByStudentId(int studentId) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Subject> subjects = new ArrayList<>();

        String query = "SELECT * FROM " + Subject.TABLE_NAME + " INNER JOIN " + Subject_Student.TABLE_NAME +
                " ON " + Subject.TABLE_NAME + "." + Subject.COL_ID + " = " + Subject_Student.TABLE_NAME + "." + Subject_Student.COL_SUBJECT_ID +
                " WHERE " + Subject_Student.TABLE_NAME + "." + Subject_Student.COL_STUDENT_ID + " = ?";

        String[] selectionArgs = { String.valueOf(studentId) };

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Subject.COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(Subject.COL_SUBJECT_NAME));

                Subject subject = new Subject(name,id);
                subjects.add(subject);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return subjects;
    }
    public ArrayList<Students> getStudentsBySubject(int subjectId) {
        ArrayList<Students> studentsList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + Students.TABLE_NAME +
                " INNER JOIN " + Subject_Student.TABLE_NAME +
                " ON " + Students.TABLE_NAME + "." + Students.COL_ID + " = " + Subject_Student.TABLE_NAME + "." + Subject_Student.COL_STUDENT_ID +
                " WHERE " + Subject_Student.TABLE_NAME + "." + Subject_Student.COL_SUBJECT_ID + " = " + subjectId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_ID));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_LAST_NAME));
                String parthDay = cursor.getString(cursor.getColumnIndexOrThrow(Students.COL_PARTH_DAY));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(Students.COL_AGE));

                Students student = new Students(id, firstName, lastName, age,parthDay);
                studentsList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentsList;
    }


    public ArrayList<Subject> getSubjects() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Subject> subjects = new ArrayList<>();

        String query = "SELECT * FROM " + Subject_Student.TABLE_NAME +
                " WHERE " + Subject_Student.COL_STUDENT_ID + " = " + Students.COL_ID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_SUBJECT_ID));
                String subjectName=cursor.getString(cursor.getColumnIndexOrThrow(Subject.COL_SUBJECT_NAME));


                Subject subject = new Subject(subjectName,subjectId);
                subjects.add(subject);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return subjects;
    }
    //  ******************************* Subject_Student دوال ******************************

    public boolean insertStudent_Subject(int studentId, int subjectId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject_Student.COL_STUDENT_ID, studentId);
        values.put(Subject_Student.COL_SUBJECT_ID, subjectId);


        long row = db.insert(Subject_Student.TABLE_NAME, null, values);
        return row > 0;
    }
    public long addAttendance(Subject_Student subject_dtudent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject_Student.COL_STUDENT_ID, subject_dtudent.getStudentId());
        values.put(Subject_Student.COL_SUBJECT_ID, subject_dtudent.getSubjectId());
        values.put(Subject_Student.COL_PRESENT, 1);

        long id = db.insert(Subject_Student.TABLE_NAME, null, values);
        db.close();

        return id;
    }
    public boolean updateStudents_Subject(String id, int studentId, int subjectId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Subject_Student.COL_STUDENT_ID, studentId);
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
                int student_id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_SUBJECT_ID));
                int subject_id=cursor.getInt(cursor.getColumnIndexOrThrow(Subject_Student.COL_STUDENT_ID));
//                boolean present=cursor.getBlob(cursor.getColumnIndexOrThrow(Subject_Student.COL_PRESENT));


                Subject_Student subject_Student=new Subject_Student(id,student_id,subject_id,false);
                data.add(subject_Student);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }

    // ...






    //  ******************************* presence دوال ******************************

    public boolean insertPresence(Presence presence) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Presence.COL_MONTH, presence.getMonth());
        values.put(Presence.COL_DAY, presence.getDay());
        values.put(Presence.COL_STUDENT_ID, presence.getStudent_id());
        values.put(Presence.COL_SUBJECT_ID, presence.getSubject_id());
        values.put(Presence.COL_PRESENT, presence.isPresent() ? 1 : 0);

        long row = db.insert(Presence.TABLE_NAME, null, values);
        return row > 0;
    }

    public String getAttendancePercentage(int subjectId, int studentId) {
        SQLiteDatabase db = getReadableDatabase();

 /*       String attendedQuery = "SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE "
                + Presence.COL_SUBJECT_ID + " = " + subjectId + " AND "
                + Presence.COL_STUDENT_ID + " = " + studentId + " AND "
                + Presence.COL_PRESENT + " = 1";*/

        String attendedQuery = "SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE "
                + Presence.COL_SUBJECT_ID + " = " + subjectId + " AND "
                + Presence.COL_STUDENT_ID + " = " + studentId + " AND "
                + Presence.COL_PRESENT + " = 1";
        Log.e("TAG", "getAttendancePercentage: "+attendedQuery );
        Cursor attendedCursor = db.rawQuery(attendedQuery, null);
        attendedCursor.moveToFirst();
        int attendedDays = attendedCursor.getInt(0);
        attendedCursor.close();

        String totalQuery = "SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE "
                + Presence.COL_SUBJECT_ID + " = " + subjectId;
        Cursor totalCursor = db.rawQuery(totalQuery, null);
        totalCursor.moveToFirst();
        int totalDays = 30;
        totalCursor.close();

        if (totalDays == 0) {
            return "0.0";
        }

        double percentage = (double) attendedDays / (double) totalDays * 100.0;

        String formattedPercentage = String.format("%.2f", percentage);

        return formattedPercentage;
    }

    public float getAttendanceRateByMonthAndSubject(String month, int subjectId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {Presence.COL_PRESENT};
        String selection = Presence.COL_MONTH + " = ? AND " + Presence.COL_SUBJECT_ID + " = ?";
        String[] selectionArgs = {month, String.valueOf(subjectId)};

        Cursor cursor = db.query(Presence.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int totalDays = cursor.getCount();
        int totalPresentDays = 0;

        if (cursor.moveToFirst()) {
            do {
                int present = cursor.getInt(cursor.getColumnIndex(Presence.COL_PRESENT));
                if (present == 1) {
                    totalPresentDays++;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (totalDays > 0) {
            return (float) totalPresentDays / totalDays;
        } else {
            return 0;
        }
    }

    public void updatePresence(Presence presence) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Presence.COL_PRESENT, presence.isPresent() ? 1 : 0);

        db.update(Presence.TABLE_NAME, values,
                Presence.COL_STUDENT_ID + " = ? AND " + Presence.COL_SUBJECT_ID + " = ? AND " + Presence.COL_MONTH + " = ? AND " + Presence.COL_DAY + " = ?",
                new String[]{String.valueOf(presence.getStudent_id()), String.valueOf(presence.getSubject_id()), presence.getMonth(), String.valueOf(presence.getDay())});

        db.close();
    }

    public boolean deletePresence(String id){
        SQLiteDatabase db=getWritableDatabase();
        int rowID=db.delete(Presence.TABLE_NAME,Presence.COL_ID+"?=",new String[]{id});
        return rowID>0;
    }
    public float getAttendanceRate(int studentId, int subjectId, String month) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE " +
                Presence.COL_STUDENT_ID + " = " + studentId + " AND " +
                Presence.COL_SUBJECT_ID + " = " + subjectId + " AND " +
                Presence.COL_MONTH + " = '" + month + "' AND " +
                Presence.COL_PRESENT + " = 1", null);
        float attendanceRate = 0;
        if (cursor.moveToFirst()) {
            int presentCount = cursor.getInt(0);
            cursor.close();
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE " +
                    Presence.COL_STUDENT_ID + " = " + studentId + " AND " +
                    Presence.COL_SUBJECT_ID + " = " + subjectId + " AND " +
                    Presence.COL_MONTH + " = '" + month + "'", null);
            if (cursor.moveToFirst()) {
                int totalCount = cursor.getInt(0);
                cursor.close();
                if (totalCount != 0) {
                    attendanceRate = (float) presentCount / totalCount;
                }
            }
        }

        return attendanceRate;
    }

    public boolean updateAttendanceRate(int studentId, int subjectId, String month, int day) {
        SQLiteDatabase db = this.getWritableDatabase();

        // حساب نسبة الحضور وتحديثها في الجدول
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE " +
                Presence.COL_STUDENT_ID + " = " + studentId + " AND " +
                Presence.COL_SUBJECT_ID + " = " + subjectId + " AND " +
                Presence.COL_MONTH + " = '" + month + "' AND " +
                Presence.COL_PRESENT + " = 1", null);
        float attendanceRate = 0;
        if (cursor.moveToFirst()) {
            int presentCount = cursor.getInt(0);
            cursor.close();
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + Presence.TABLE_NAME + " WHERE " +
                    Presence.COL_STUDENT_ID + " = " + studentId + " AND " +
                    Presence.COL_SUBJECT_ID + " = " + subjectId + " AND " +
                    Presence.COL_MONTH + " = '" + month + "'", null);
            if (cursor.moveToFirst()) {
                int totalCount = cursor.getInt(0);
                cursor.close();
                if (totalCount != 0) {
                    attendanceRate = (float) presentCount / totalCount;
                }
            }
        }

        ContentValues values = new ContentValues();
        values.put(DpHelper.COL_ATTENDANCE_RATE, attendanceRate);

        int count = db.update(Presence.TABLE_NAME, values,
                Presence.COL_STUDENT_ID + " = ? AND " +
                        Presence.COL_SUBJECT_ID + " = ? AND " +
                        Presence.COL_MONTH + " = ? AND " +
                        Presence.COL_DAY + " = ?",
                new String[]{String.valueOf(studentId), String.valueOf(subjectId), month, String.valueOf(day)});

        return count > 0;
    }


}
