package com.foxminded.university.web.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Course;
import com.foxminded.university.service.CourseService;

@WebServlet("/courses")
public class CoursesServlet extends HttpServlet {
    
    private CourseService courseService;
    
    @Override
    public void init() throws ServletException {
        courseService = new CourseService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> courses = courseService.findAll();
        request.setAttribute("courses", courses);
        getServletContext().getRequestDispatcher("/courses.jsp").forward(request, response);
    }
}
