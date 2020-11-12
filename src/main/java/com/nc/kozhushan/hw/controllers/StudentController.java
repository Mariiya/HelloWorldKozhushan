package com.nc.kozhushan.hw.controllers;

import java.util.List;

import com.nc.kozhushan.hw.dao.StudentDaoImpl;
import com.nc.kozhushan.hw.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StudentController {

    @Autowired
    StudentDaoImpl dao;


    @RequestMapping("/std_form")
    public String showForm(Model m) {
        m.addAttribute("command", new Student());
        return "std_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("std") Student std) {
        dao.save(std);
        return "redirect:/view_std";
    }

    @RequestMapping("/view_std")
    public String view_std(Model m) {
        List<Student> list = dao.getStudents();
        m.addAttribute("list", list);
        return "view_std";
    }


    @RequestMapping(value = "/edit_std/{id}")
    public String edit(@PathVariable int id, Model m) {
        Student std = dao.getStudentById(id);
        m.addAttribute("command", std);
        return "std_edit_form";
    }

    /* It updates model object. */
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("std") Student std) {
        dao.update(std);
        return "redirect:/view_std";
    }

    @RequestMapping(value = "/delete_std/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/view_std";
    }
}