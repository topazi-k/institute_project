package com.foxminded.university.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyService;

@WebServlet("/faculties")
public class FacultiesServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculty> faculties = new FacultyService().findAll();
        request.setAttribute("faculties", faculties);
        getServletContext().getRequestDispatcher("/faculties.jsp").forward(request, response);
    }
}
