package com.foxminded.university.web.student;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    
    private StudentService studentService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        studentService = new StudentService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (Integer.parseInt(request.getParameter("id")));
        Student student = studentService.findById(id);
        studentService.delete(student);
        response.sendRedirect(request.getContextPath() + "/students");
        
    }
    
}
