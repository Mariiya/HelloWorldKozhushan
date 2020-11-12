package com.nc.kozhushan.hw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.nc.kozhushan.hw.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StudentDaoImpl implements StudentDao {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public int save(Student s) {
        String sql = "insert into Students values(SEQ_STUDENT_ID.nextval,'" + s.getName() + "'," + s.getAge() + ",'" + s.getEmail() + "')";
        return template.update(sql);
    }

    public int update(Student s) {
        String sql = "update Students set name='" + s.getName() + "', age=" + s.getAge() + ", email='" + s.getEmail() + "' where id=" + s.getId();
        return template.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from Students where id=" + id + "";
        return template.update(sql);
    }

    public Student getStudentById(int id) {
        String sql = "select * from Students where id=?";
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Student>(Student.class));
    }

    public List<Student> getStudents() {
        return template.query("select * from Students", new RowMapper<Student>() {
            public Student mapRow(ResultSet rs, int row) throws SQLException {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setEmail(rs.getString(4));
                return s;
            }
        });
    }
}