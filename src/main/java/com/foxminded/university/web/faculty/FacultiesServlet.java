package com.foxminded.university.web.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyService;

@WebServlet("/faculties")
public class FacultiesServlet extends HttpServlet {
    
    FacultyService facultyService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        facultyService = new FacultyService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Faculty> faculties = facultyService.findAll();
        request.setAttribute("faculties", faculties);
        getServletContext().getRequestDispatcher("/faculties.jsp").forward(request, response);
    }
}
