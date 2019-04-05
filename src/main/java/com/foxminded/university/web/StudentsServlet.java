package com.foxminded.university.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@WebServlet("/groups/students")
public class StudentsServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int groupId = Integer.parseInt(request.getParameter("id"));
        Group group = new GroupService().findById(groupId);
        request.setAttribute("group", group);
        getServletContext().getRequestDispatcher("/students.jsp").forward(request, response);
    }
    
}
