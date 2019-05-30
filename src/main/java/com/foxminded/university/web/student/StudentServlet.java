package com.foxminded.university.web.student;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.constants.Constants;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.DataNotFoundException;
import com.foxminded.university.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Student student;
        try {
            int studentId = Integer.parseInt(request.getParameter("id"));
            student = studentService.findById(studentId);
        } catch (DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        request.setAttribute("student", student);
        getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Student student = new Student();
        int id;
        LocalDate birthDay;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            student.setFirstName(request.getParameter("first_name"));
            student.setLastName(request.getParameter("last_name"));
            birthDay = LocalDate.parse(request.getParameter("birthday"), Constants.DATE_FORMATTER);
        } catch (NumberFormatException | DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        student.setId(id);
        student.setBirthDay(birthDay);
        studentService.update(student);
        response.sendRedirect(request.getContextPath() + "/student?id=" + id);
    }
    
}
