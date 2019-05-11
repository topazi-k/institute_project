package com.foxminded.university.web.student;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    
    private StudentService studentService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        studentService = new StudentService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentService.findAll());
        getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName(request.getParameter("first_name"));
        student.setLastName(request.getParameter("last_name"));
        
        int dayOfMonth = Integer.parseInt(request.getParameter("day"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        student.setBirthDay(LocalDate.of(year, month, dayOfMonth));
        studentService.create(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }
    
}
