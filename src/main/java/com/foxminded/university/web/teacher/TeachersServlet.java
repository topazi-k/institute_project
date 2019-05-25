package com.foxminded.university.web.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.LectureService;
import com.foxminded.university.service.TeacherService;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {
    
    private TeacherService teacherService;
    
    @Override
    public void init() throws ServletException {
        teacherService = new TeacherService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherService.findAll();
        request.setAttribute("teachers", teachers);
        getServletContext().getRequestDispatcher("/teachers.jsp").forward(request, response);
    }
}
