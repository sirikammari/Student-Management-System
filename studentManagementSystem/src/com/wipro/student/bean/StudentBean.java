package com.wipro.student.bean;

public class StudentBean {

    private String studentId;
    private String name;
    private int mark1;
    private int mark2;
    private int mark3;
    private String result;
    private String grade;
    private String course;


    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMark1() { return mark1; }
    public void setMark1(int mark1) { this.mark1 = mark1; }

    public int getMark2() { return mark2; }
    public void setMark2(int mark2) { this.mark2 = mark2; }

    public int getMark3() { return mark3; }
    public void setMark3(int mark3) { this.mark3 = mark3; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}
