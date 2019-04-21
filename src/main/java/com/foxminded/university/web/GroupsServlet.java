package com.foxminded.university.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@WebServlet("/groups")
public class GroupsServlet extends HttpServlet {
    
    private GroupService groupService = new GroupService();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String crudType = request.getParameter("crud_type");
        
        if (crudType == null) {
            findAll(request, response);
        } else if (crudType.equals("create")) {
            create(request, response);
        } else if (crudType.equals("delete")) {
            
        }
        
    }
    
    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("groups", groupService.findAll());
        getServletContext().getRequestDispatcher("/groups.jsp").forward(request, response);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String groupName = request.getParameter("name");
        int groupNumber = Integer.parseInt(request.getParameter("number"));
        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupNumber(groupNumber);
        groupService.create(group);
        findAll(request, response);
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        Group group = groupService.findById(groupId);
        groupService.removeAllStudents(group);
        groupService.delete(group);
        findAll(request, response);
    }
}
