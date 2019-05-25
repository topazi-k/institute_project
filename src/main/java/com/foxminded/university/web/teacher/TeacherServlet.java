package com.foxminded.university.web.teacher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.TeacherService;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {
    
    private TeacherService teacherService;
    
    @Override
    public void init() throws ServletException {
        teacherService = new TeacherService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherService.findById(teacherId);
        request.setAttribute("teacher", teacher);
        getServletContext().getRequestDispatcher("/teacher.jsp").forward(request, response);
    }
}
