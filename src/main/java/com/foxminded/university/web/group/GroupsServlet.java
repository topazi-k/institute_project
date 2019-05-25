package com.foxminded.university.web.group;

import java.io.IOException;

import javax.servlet.ServletConfig;
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
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = new Group();
        try {
            String groupName = request.getParameter("name");
            int groupNumber = Integer.parseInt(request.getParameter("number"));
            group.setGroupName(groupName);
            group.setGroupNumber(groupNumber);
            
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }
        groupService.create(group);
        response.sendRedirect(request.getContextPath() + "/groups");
    }
    
}
