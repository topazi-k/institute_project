package com.foxminded.university.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.service.FacultyService;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int facultyId = Integer.parseInt(request.getParameter("id"));
        Faculty faculty = new FacultyService().findById(facultyId);
        request.setAttribute("faculty", faculty);
        getServletContext().getRequestDispatcher("/faculty.jsp").forward(request, response);
    }
}
