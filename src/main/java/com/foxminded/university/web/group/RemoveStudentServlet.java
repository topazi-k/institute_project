package com.foxminded.university.web.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.service.DataNotFoundException;
import com.foxminded.university.service.GroupService;
import com.foxminded.university.service.StudentService;

@WebServlet("/group/remove_student")
public class RemoveStudentServlet extends HttpServlet {
    
    private GroupService groupService = new GroupService();
    private StudentService studentService = new StudentService();
    
    @Override
    public void init() throws ServletException {
        groupService = new GroupService();
        studentService = new StudentService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Student student;
        int groupId;
        try {
            groupId = Integer.parseInt(request.getParameter("id"));
            int studentId = Integer.parseInt(request.getParameter("student_id"));
            student = studentService.findById(studentId);
        } catch (DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        groupService.removeStudent(student);
        response.sendRedirect(request.getContextPath() + "/group?id=" + groupId);
        
    }
    
}
