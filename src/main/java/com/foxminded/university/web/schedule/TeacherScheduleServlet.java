package com.foxminded.university.web.schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.domain.Lecture;
import com.foxminded.university.service.TimePeriodService;
import com.foxminded.university.service.UniversityService;

@WebServlet("/teacher_schedule")
public class TeacherScheduleServlet extends HttpServlet {
    
    private UniversityService universityService;
    private DateTimeFormatter formatter;
    
    @Override
    public void init() throws ServletException {
        universityService = new UniversityService();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Lecture> schedule = new ArrayList<>();
        TimePeriodService timePeriod;
        try {
            if (request.getParameter("period").equals("day")) {
                LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
                timePeriod = new TimePeriodService(date);
                
            } else {
                
                LocalDate dateStart = LocalDate.parse(request.getParameter("date_start"), formatter);
                LocalDate dateEnd = LocalDate.parse(request.getParameter("date_end"), formatter);
                timePeriod = new TimePeriodService(dateStart, dateEnd);
            }
            int teacherId = Integer.parseInt(request.getParameter("id"));
            schedule = universityService.getTeacherSchedule(teacherId, timePeriod);
        } catch (com.foxminded.university.service.DataNotFoundException e) {
            response.sendError(404);
            return;
        } catch (DateTimeParseException | NumberFormatException e) {
            response.sendError(400);
            return;
        }
        request.setAttribute("schedule", schedule);
        getServletContext().getRequestDispatcher("/teacher_schedule.jsp").forward(request, response);
    }
}
