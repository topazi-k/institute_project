package com.foxminded.university.web.group;

import java.io.IOException;

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
    public void init() throws ServletException {
        groupService = new GroupService();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setAttribute("groups", groupService.findAll());
        getServletContext().getRequestDispatcher("/groups.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Group group = new Group();
        int groupNumber;
        String groupName;
        try {
            groupName = request.getParameter("name");
            groupNumber = Integer.parseInt(request.getParameter("number"));
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }
        group.setGroupName(groupName);
        group.setGroupNumber(groupNumber);
        groupService.create(group);
        response.sendRedirect(request.getContextPath() + "/groups");
    }
    
}
