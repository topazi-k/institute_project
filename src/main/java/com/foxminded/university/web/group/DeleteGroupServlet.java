package com.foxminded.university.web.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Group;
import com.foxminded.university.service.DataNotFoundException;
import com.foxminded.university.service.GroupService;

@WebServlet("/group/delete")
public class DeleteGroupServlet extends HttpServlet {
    
    private GroupService groupService;
    
    @Override
    public void init() throws ServletException {
        groupService = new GroupService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Group group;
        try {
            int groupId = Integer.parseInt(request.getParameter("id"));
            group = groupService.findById(groupId);
        } catch (DataNotFoundException e) {
            response.sendError(404);
            return;
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }
        groupService.removeAllStudents(group);
        groupService.delete(group);
        response.sendRedirect(request.getContextPath() + "/groups");
    }
    
}
