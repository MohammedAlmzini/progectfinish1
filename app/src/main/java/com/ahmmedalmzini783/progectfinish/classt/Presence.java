package com.ahmmedalmzini783.progectfinish.classt;

public class Presence {
    private int id;
    private String month;
    private int day;
    private int student_id;
    private int subject_id;


    public static final String TABLE_NAME="presence";
    public static final String COL_ID="id";
    public static final String COL_MONTH="month";
    public static final String COL_DAY="day";
    public static final String COL_STUDENT_ID="student_id";
    public static final String COL_SUBJECT_ID="subject_id";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_MONTH + " TEXT, "
            + COL_DAY + " INTEGER, "
            + COL_STUDENT_ID + " INTEGER, "
            + COL_SUBJECT_ID + " INTEGER, "
            + "FOREIGN KEY(" + COL_STUDENT_ID + ") REFERENCES " + Students.TABLE_NAME + "(" + Students.COL_ID + "), "
            + "FOREIGN KEY(" + COL_SUBJECT_ID + ") REFERENCES " + Subject.TABLE_NAME + "(" + Subject.COL_ID + "))";



    public Presence(int id, String month, int day, int student_id, int subject_id) {
        this.id = id;
        this.month = month;
        this.day = day;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }

    public Presence(int day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
