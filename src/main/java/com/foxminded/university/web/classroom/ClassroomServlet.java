package com.foxminded.university.web.classroom;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@WebServlet("/classroom")
public class ClassroomServlet extends HttpServlet {
    
    private ClassroomService classroomService;
    
    @Override
    public void init() throws ServletException {
        classroomService = new ClassroomService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomId = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = classroomService.findById(classroomId);
        request.setAttribute("classroom", classroom);
        getServletContext().getRequestDispatcher("/classroom.jsp").forward(request, response);
    }
}
