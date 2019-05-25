package com.foxminded.university.web.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;

@WebServlet("/group/add_student")
public class AddStudentServlet extends HttpServlet {
    
    private GroupService groupService;
    private StudentService studentService;
    
    @Override
    public void init() throws ServletException {
        groupService = new GroupService();
        studentService = new StudentService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Group group = null;
        Student student = null;
        try {
            int groupId = Integer.parseInt(request.getParameter("id"));
            int studentId = Integer.parseInt(request.getParameter("student_id"));
            group = groupService.findById(groupId);
            student = studentService.findById(studentId);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(404);
            return;
        } catch (NumberFormatException e) {
            response.sendError(400);
            return;
        }
        groupService.addStudent(group, student);
        response.sendRedirect(request.getContextPath() + "/group?id=" + groupId);
    }
    
}
