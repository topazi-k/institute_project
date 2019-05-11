package com.foxminded.university.web.classroom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@WebServlet("/classrooms")
public class ClassroomsServlet extends HttpServlet {
    
    ClassroomService classroomService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        classroomService = new ClassroomService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classroom> classroomms = classroomService.findAll();
        request.setAttribute("classrooms", classroomms);
        getServletContext().getRequestDispatcher("/classrooms.jsp").forward(request, response);
    }
}
