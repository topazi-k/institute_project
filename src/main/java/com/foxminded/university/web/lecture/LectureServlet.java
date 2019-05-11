package com.foxminded.university.web.lecture;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Lecture;
import com.foxminded.university.service.LectureService;

@WebServlet("/lecture")
public class LectureServlet extends HttpServlet {
    
    LectureService lectureService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lectureService = new LectureService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lectureId = Integer.parseInt(request.getParameter("id"));
        Lecture lecture = lectureService.findById(lectureId);
        request.setAttribute("lecture", lecture);
        getServletContext().getRequestDispatcher("/lecture.jsp").forward(request, response);
        
    }
}
