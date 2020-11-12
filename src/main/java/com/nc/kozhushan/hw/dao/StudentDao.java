package com.nc.kozhushan.hw.dao;

import com.nc.kozhushan.hw.model.Student;

import java.util.List;


public interface StudentDao {

    int save(Student s);

    int update(Student s);

    int delete(int id);

    Student getStudentById(int id);

    List<Student> getStudents();
}

