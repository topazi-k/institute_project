package com.foxminded.university.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    
    private StudentService studentService = new StudentService();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crudType = request.getParameter("crud_type");
        if (crudType == null) {
            findAll(request, response);
        } else if (crudType.equals("create")) {
            create(request, response);
        } else if (crudType.equals("delete")) {
            delete(request, response);
        }
        
    }
    
    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("students", studentService.findAll());
        getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(request.getParameter("first_name"));
        student.setLastName(request.getParameter("last_name"));
        
        int dayOfMonth = Integer.parseInt(request.getParameter("day"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        student.setBirthDay(LocalDate.of(year, month, dayOfMonth));
        studentService.create(student);
        findAll(request, response);
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (Integer.parseInt(request.getParameter("id")));
        Student student = studentService.findById(id);
        studentService.delete(student);
        findAll(request, response);
    }
}
