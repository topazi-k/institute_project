package com.foxminded.university.web.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.DataNotFoundException;
import com.foxminded.university.service.StudentService;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student;
        try {
            int id = (Integer.parseInt(request.getParameter("id")));
            student = studentService.findById(id);
        } catch (DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        studentService.delete(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}
