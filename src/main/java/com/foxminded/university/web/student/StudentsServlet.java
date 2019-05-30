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
import com.foxminded.university.service.StudentService;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students", studentService.findAll());
        getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student = new Student();
        LocalDate birthDay;
        try {
            student.setFirstName(request.getParameter("first_name"));
            student.setLastName(request.getParameter("last_name"));
            birthDay = LocalDate.parse(request.getParameter("birthday"), Constants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        student.setBirthDay(birthDay);
        studentService.create(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }
    
}
