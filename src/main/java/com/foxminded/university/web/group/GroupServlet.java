package com.foxminded.university.web.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    
    private GroupService groupService = new GroupService();
    private StudentService studentService = new StudentService();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int groupId = Integer.parseInt(request.getParameter("id"));
        Group group;
        try {
            group = groupService.findById(groupId);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<Student> freeStudents = studentService.findStudentsWithoutGroup();
        request.setAttribute("free_students", freeStudents);
        request.setAttribute("group", group);
        getServletContext().getRequestDispatcher("/group.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        int groupNumber = Integer.parseInt(request.getParameter("number"));
        String groupName = request.getParameter("name");
        Group group = new Group();
        group.setId(groupId);
        group.setGroupNumber(groupNumber);
        group.setGroupName(groupName);
        groupService.update(group);
        
        try {
            group = groupService.findById(groupId);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<Student> freeStudents = studentService.findStudentsWithoutGroup();
        request.setAttribute("free_students", freeStudents);
        request.setAttribute("group", group);
        getServletContext().getRequestDispatcher("/group.jsp").forward(request, response);
    }
    
}
