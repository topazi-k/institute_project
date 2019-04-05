package com.foxminded.university.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = new StudentService().findById(studentId);
        request.setAttribute("student", student);
        getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
    }
}
