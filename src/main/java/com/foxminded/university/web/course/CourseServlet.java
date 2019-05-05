package com.foxminded.university.web.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Course;
import com.foxminded.university.service.CourseService;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = new CourseService().findById(courseId);
        request.setAttribute("course", course);
        getServletContext().getRequestDispatcher("/course.jsp").forward(request, response);
    }
}
