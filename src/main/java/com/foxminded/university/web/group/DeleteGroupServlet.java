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

@WebServlet("/group/delete")
public class DeleteGroupServlet extends HttpServlet {
    
    GroupService groupService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        groupService = new GroupService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int groupId = Integer.parseInt(request.getParameter("id"));
        Group group = groupService.findById(groupId);
        groupService.removeAllStudents(group);
        groupService.delete(group);
        response.sendRedirect(request.getContextPath() + "/groups");
    }
    
}
