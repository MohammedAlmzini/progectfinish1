package com.ahmmedalmzini783.progectfinish.classt;

public class Subject {
    private String subjectName;
    private int id;
    private boolean selected;

    public static final String TABLE_NAME = "subject";
    public static final String COL_SUBJECT_NAME = "subjectName";
    public static final String COL_ID = "id";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SUBJECT_NAME + " TEXT)";

    public Subject(String subjectName, int id) {
        this.subjectName = subjectName;
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", id=" + id +
                '}';
    }
}
