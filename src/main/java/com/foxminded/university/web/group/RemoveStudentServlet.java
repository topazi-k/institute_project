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

@WebServlet("/group/remove_student")
public class RemoveStudentServlet extends HttpServlet {
    
    GroupService groupService = new GroupService();
    StudentService studentService = new StudentService();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        groupService = new GroupService();
        studentService = new StudentService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int groupId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        Student student = studentService.findById(studentId);
        groupService.removeStudent(student);
        response.sendRedirect(request.getContextPath() + "/group?id=" + groupId);
        
    }
    
}
