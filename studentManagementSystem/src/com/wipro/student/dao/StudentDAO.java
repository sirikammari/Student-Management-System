package com.wipro.student.dao;

import java.sql.*;
import java.util.ArrayList;
import com.wipro.student.bean.StudentBean;
import com.wipro.student.util.DBUtil;

public class StudentDAO {

    public String addStudent(StudentBean sb) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "INSERT INTO STUDENT_TBL(NAME,COURSE,MARK1,MARK2,MARK3,RESULT,GRADE) "
                       + "VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, sb.getName());
            ps.setString(2, sb.getCourse());
            ps.setInt(3, sb.getMark1());
            ps.setInt(4, sb.getMark2());
            ps.setInt(5, sb.getMark3());
            ps.setString(6, sb.getResult());
            ps.setString(7, sb.getGrade());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int seq = rs.getInt(1);
                sb.setStudentId(sb.getName().substring(0, 2).toUpperCase() + seq);

                PreparedStatement ps2 =
                        con.prepareStatement("UPDATE STUDENT_TBL SET STUDENT_ID=? WHERE SEQ_ID=?");
                ps2.setString(1, sb.getStudentId());
                ps2.setInt(2, seq);
                ps2.executeUpdate();
            }

            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace(); // keep this for debugging
            return "FAIL";
        }
    }

    public ArrayList<StudentBean> getStudents(String criteria) {
        ArrayList<StudentBean> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            String sql = criteria.equals("ALL")
                    ? "SELECT * FROM STUDENT_TBL"
                    : "SELECT * FROM STUDENT_TBL WHERE RESULT=?";

            PreparedStatement ps = con.prepareStatement(sql);
            if (!criteria.equals("ALL")) {
                ps.setString(1, criteria);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean sb = new StudentBean();
                sb.setStudentId(rs.getString("STUDENT_ID"));
                sb.setName(rs.getString("NAME"));
                sb.setCourse(rs.getString("COURSE"));
                sb.setResult(rs.getString("RESULT"));
                sb.setGrade(rs.getString("GRADE"));
                list.add(sb);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
