package com.foxminded.university.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.service.ClassroomService;

@WebServlet("/classrooms")
public class ClassroomsServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classroom> classroomms = new ClassroomService().findAll();
        request.setAttribute("classrooms", classroomms);
        getServletContext().getRequestDispatcher("/classrooms.jsp").forward(request, response);
    }
}
