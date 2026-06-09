package com.wipro.student.service;

import java.util.Scanner;
import com.wipro.student.bean.StudentBean;
import com.wipro.student.dao.StudentDAO;
import com.wipro.student.util.InvalidDataException;

public class StudentMain {

    public String addStudent(StudentBean sb) {
        try {
            if (sb == null || sb.getName().length() < 2 ||
                sb.getMark1()<0 || sb.getMark1()>100 ||
                sb.getMark2()<0 || sb.getMark2()>100 ||
                sb.getMark3()<0 || sb.getMark3()>100) {
                throw new InvalidDataException();
            }
        } catch (InvalidDataException e) {
            return e.toString();
        }

        int total = sb.getMark1() + sb.getMark2() + sb.getMark3();
        if (total >= 240) { sb.setResult("PASS"); sb.setGrade("Distinction"); }
        else if (total >= 180) { sb.setResult("PASS"); sb.setGrade("First Class"); }
        else if (total >= 150) { sb.setResult("PASS"); sb.setGrade("Second Class"); }
        else if (total >= 105) { sb.setResult("PASS"); sb.setGrade("Third Class"); }
        else { sb.setResult("FAIL"); sb.setGrade("No Grade"); }

        StudentDAO dao = new StudentDAO();
        return dao.addStudent(sb).equals("SUCCESS")
                ? sb.getStudentId() + ":" + sb.getResult()
                : "Error";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentMain sm = new StudentMain();
        StudentBean sb = new StudentBean();

        System.out.println("Enter Name:");
        sb.setName(sc.nextLine());
        System.out.println("Enter Mark1:");
        sb.setMark1(sc.nextInt());
        System.out.println("Enter Mark2:");
        sb.setMark2(sc.nextInt());
        System.out.println("Enter Mark3:");
        sb.setMark3(sc.nextInt());
        
        System.out.println("Select Course:");
        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.println("3. Web Development");
        System.out.println("4. Data Science");

        int choice = sc.nextInt();
        sc.nextLine(); // important

        switch (choice) {
            case 1: sb.setCourse("Java"); break;
            case 2: sb.setCourse("Python"); break;
            case 3: sb.setCourse("Web Development"); break;
            case 4: sb.setCourse("Data Science"); break;
            default: sb.setCourse("Java");
        }


        System.out.println(sm.addStudent(sb));
        System.out.println("COURSE : " + sb.getCourse());
        sc.close();
    }
}
