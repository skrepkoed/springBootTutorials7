package com.example.demo7.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo7.dao.StudentRepository;
import com.example.demo7.entity.Student;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public ModelAndView getAllStudents(){
        ModelAndView mav=new ModelAndView("list-students");
        mav.addObject("students", studentRepository.findAll());
        return mav;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm(){
        ModelAndView mav=new ModelAndView("add-student-form");
        Student student =new Student();
        mav.addObject("student", student);
        return mav;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long studentId){
        ModelAndView mav=new ModelAndView("add-student-form");
        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        Student student =new Student();
        if (optionalStudent.isPresent()) {
            student=optionalStudent.get();
        }
        mav.addObject("student", student);
        return mav;
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId){
        studentRepository.deleteById(studentId);;
        return "redirect:/list";
    }
    
}
