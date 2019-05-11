package com.foxminded.university.web.lecture;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Lecture;
import com.foxminded.university.service.FacultyService;
import com.foxminded.university.service.LectureService;

@WebServlet("/lectures")
public class LecturesServlet extends HttpServlet {
    
    LectureService lectureService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lectureService = new LectureService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecture> lectures = lectureService.findAll();
        request.setAttribute("lectures", lectures);
        getServletContext().getRequestDispatcher("/lectures.jsp").forward(request, response);
    }
}
