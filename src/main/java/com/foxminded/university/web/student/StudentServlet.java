package com.foxminded.university.web.student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    
    private StudentService studentService;
    private DateTimeFormatter formatter;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Student student;
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            student = studentService.findById(studentId);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(404);
            return;
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }
        request.setAttribute("student", student);
        getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Student student = new Student();
        int id = 0;
        
        try {
            id = (Integer.parseInt(request.getParameter("id")));
            student.setId(id);
            student.setFirstName(request.getParameter("first_name"));
            student.setLastName(request.getParameter("last_name"));
            LocalDate birthDay = LocalDate.parse(request.getParameter("birthday"), formatter);
            student.setBirthDay(birthDay);
        } catch (NumberFormatException | DateTimeParseException e) {
            response.sendError(400);
        }
        studentService.update(student);
        response.sendRedirect(request.getContextPath() + "/student?id=" + id);
    }
    
}
