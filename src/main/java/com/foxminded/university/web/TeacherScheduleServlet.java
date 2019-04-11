package com.foxminded.university.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Lecture;
import com.foxminded.university.service.TimePeriodService;
import com.foxminded.university.service.UniversityService;

@WebServlet("/teacher_schedule")
public class TeacherScheduleServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int teacherId=Integer.parseInt(request.getParameter("id"));
        List<Lecture> schedule=new ArrayList<>();
        TimePeriodService timePeriod;
        if (request.getParameter("period").equals("day")) {
            int dayOfMonth = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            timePeriod = new TimePeriodService(date);
            
           
        }else {
            int dayOfMonth = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            int dayOfMonthLast = Integer.parseInt(request.getParameter("last_day"));
            int monthLast = Integer.parseInt(request.getParameter("last_month"));
            int yearLast = Integer.parseInt(request.getParameter("last_year"));
            LocalDate dateStart = LocalDate.of(year, month, dayOfMonth);
            LocalDate dateEnd = LocalDate.of(yearLast, monthLast, dayOfMonthLast);
            timePeriod = new TimePeriodService(dateStart,dateEnd);
        }
        schedule = new UniversityService().getTeacherSchedule(teacherId, timePeriod);
        request.setAttribute("schedule", schedule);
        getServletContext().getRequestDispatcher("/teacher_schedule.jsp").forward(request, response);
    }
}
