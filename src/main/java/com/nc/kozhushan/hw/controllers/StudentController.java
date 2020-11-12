package com.nc.kozhushan.hw.controllers;

import java.util.List;

import com.nc.kozhushan.hw.dao.StudentDao;
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
    StudentDao dao;//will inject dao from XML file


        @RequestMapping("/stdform")
        public String showform(Model m){
            m.addAttribute("command", new Student());
            return "stdform";
        }
        /*It saves object into database. The @ModelAttribute puts request data
         *  into model object. You need to mention RequestMethod.POST method
         *  because default request is GET*/
        @RequestMapping(value="/save",method = RequestMethod.POST)
        public String save(@ModelAttribute("std") Student std){
            dao.save(std);
            return "redirect:/viewstd";//will redirect to viewemp request mapping
        }
        /* It provides list of employees in model object */
        @RequestMapping("/viewstd")
        public String viewstd(Model m){
            List<Student> list=dao.getStudents();
            m.addAttribute("list",list);
            return "viewstd";
        }
        /* It displays object data into form for the given id.
         * The @PathVariable puts URL data into variable.*/
        @RequestMapping(value="/editstd/{id}")
        public String edit(@PathVariable int id, Model m){
            Student std=dao.getStudentById(id);
            m.addAttribute("command",std);
            return "stdeditform";
        }
        /* It updates model object. */
        @RequestMapping(value="/editsave",method = RequestMethod.POST)
        public String editsave(@ModelAttribute("std") Student std){
            dao.update(std);
            return "redirect:/viewstd";
        }
        /* It deletes record for the given id in URL and redirects to /viewemp */
        @RequestMapping(value="/deletestd/{id}",method = RequestMethod.GET)
        public String delete(@PathVariable int id){
            dao.delete(id);
            return "redirect:/viewstd";
        }
    }