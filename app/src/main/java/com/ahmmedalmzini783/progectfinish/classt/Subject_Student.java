package com.ahmmedalmzini783.progectfinish.classt;

public class Subject_Student {
    private int id;
    private int studentId;
    private int subjectId;
    private boolean present;


    public static final String TABLE_NAME="Subject_Student";
    public static final String COL_ID = "id";
    public static final String COL_STUDENT_ID = "studentId";
    public static final String COL_SUBJECT_ID = "subjectId";
    public static final String COL_PRESENT = "present";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_STUDENT_ID + " INTEGER, "
            + COL_SUBJECT_ID + " INTEGER, "
            + COL_PRESENT + " INTEGER, "
            + "FOREIGN KEY (" + COL_STUDENT_ID + ") REFERENCES " + Students.TABLE_NAME + "(" + Students.COL_ID + "), "
            + "FOREIGN KEY (" + COL_SUBJECT_ID + ") REFERENCES " + Subject.TABLE_NAME + "(" + Subject.COL_ID + "))";




    public Subject_Student(int id, int studentId, int subjectId, boolean present) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.present = present;
    }

    public Subject_Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", present=" + present +
                '}';
    }
}
