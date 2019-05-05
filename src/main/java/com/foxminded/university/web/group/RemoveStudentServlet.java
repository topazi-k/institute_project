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

@WebServlet("/group/remove_student")
public class RemoveStudentServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupService groupService = new GroupService();
        StudentService studentService = new StudentService();
        int groupId=Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        Student student = studentService.findById(studentId);
        groupService.removeStudent(student);
        
        Group group = groupService.findById(groupId);
        List<Student> freeStudents = studentService.findStudentsWithoutGroup();
        request.setAttribute("free_students", freeStudents);
        request.setAttribute("group", group);
        getServletContext().getRequestDispatcher("/group.jsp").forward(request, response);
    }
    
}
