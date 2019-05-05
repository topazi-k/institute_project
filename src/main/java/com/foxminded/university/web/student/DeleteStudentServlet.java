package com.foxminded.university.web.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService studentService = new StudentService();
        int id = (Integer.parseInt(request.getParameter("id")));
        Student student = studentService.findById(id);
        studentService.delete(student);
        
        request.setAttribute("students", studentService.findAll());
        getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
}
