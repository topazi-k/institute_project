package com.foxminded.university.web;

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
    
    GroupService groupService = new GroupService();
    StudentService studentService = new StudentService();
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String crudType = request.getParameter("crud_type");
        
        if (crudType == null) {
            findById(request, response);
        } else if (crudType.equals("update")) {
            update(request, response);
        } else if (crudType.equals("add_student")) {
            addStudent(request, response);
        } else if (crudType.equals("remove_student")) {
            removeStudent(request, response);
        }
        
    }
    
    private void findById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        int groupNumber = Integer.parseInt(request.getParameter("number"));
        String groupName = request.getParameter("name");
        Group group = new Group();
        group.setId(groupId);
        group.setGroupNumber(groupNumber);
        group.setGroupName(groupName);
        groupService.update(group);
        findById(request, response);
    }
    
    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        Group group = groupService.findById(groupId);
        Student student = studentService.findById(studentId);
        groupService.addStudent(group, student);
        findById(request, response);
    }
    
    private void removeStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        Student student = studentService.findById(studentId);
        groupService.removeStudent(student);
        findById(request, response);
    }
}
