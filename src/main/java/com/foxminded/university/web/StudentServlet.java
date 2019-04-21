package com.foxminded.university.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    
    private StudentService studentService = new StudentService();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("crud_type") == null) {
            findById(request, response);
        } else if (request.getParameter("crud_type").equals("update")) {
            update(request, response);
        }
    }
    
    private void findById(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student;
        
        try {
            student = studentService.findById(studentId);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        request.setAttribute("student", student);
        getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Student student = new Student();
        int id = (Integer.parseInt(request.getParameter("id")));
        student.setId(id);
        student.setFirstName(request.getParameter("first_name"));
        student.setLastName(request.getParameter("last_name"));
        
        int dayOfMonth = Integer.parseInt(request.getParameter("day"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));
        student.setBirthDay(LocalDate.of(year, month, dayOfMonth));
        studentService.update(student);
        findById(request, response);
    }
    
}
