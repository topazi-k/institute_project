package com.foxminded.university.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.Teacher;
import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.TeacherService;

@WebServlet("/schedule")
public class ScheduleFormServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("id").equals("student")) {
            List<Student> students = new StudentService().findAll();
            request.setAttribute("students", students);
            getServletContext().getRequestDispatcher("/student_schedule_form.jsp").forward(request, response);
        }
        else {
            List<Teacher> teachers=new TeacherService().findAll();
            request.setAttribute("teachers", teachers);
            getServletContext().getRequestDispatcher("/teacher_schedule_form.jsp").forward(request, response);
        }
    }
}
