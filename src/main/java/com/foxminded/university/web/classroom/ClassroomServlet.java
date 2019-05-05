package com.foxminded.university.web.classroom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@WebServlet("/classroom")
public class ClassroomServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomId = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = new ClassroomService().findById(classroomId);
        request.setAttribute("classroom", classroom);
        getServletContext().getRequestDispatcher("/classroom.jsp").forward(request, response);
    }
}
