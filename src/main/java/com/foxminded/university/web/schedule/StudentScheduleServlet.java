package com.foxminded.university.web.schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.university.constants.Constants;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.service.DataNotFoundException;
import com.foxminded.university.service.TimePeriodService;
import com.foxminded.university.service.UniversityService;

@WebServlet("/student_schedule")
public class StudentScheduleServlet extends HttpServlet {
    
    private UniversityService universityService;
    
    @Override
    public void init() throws ServletException {
        universityService = new UniversityService();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<Lecture> schedule = new ArrayList<>();
        TimePeriodService timePeriod = null;
        
        try {
            
            if (request.getParameter("period").equals("day")) {
                LocalDate date = LocalDate.parse(request.getParameter("date"), Constants.DATE_FORMATTER);
                timePeriod = new TimePeriodService(date);
                
            } else {
                LocalDate dateStart = LocalDate.parse(request.getParameter("date_start"), Constants.DATE_FORMATTER);
                LocalDate dateEnd = LocalDate.parse(request.getParameter("date_end"), Constants.DATE_FORMATTER);
                timePeriod = new TimePeriodService(dateStart, dateEnd);
            }
            int studentId = Integer.parseInt(request.getParameter("id"));
            schedule = universityService.getStudentSchedule(studentId, timePeriod);
        } catch (DataNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (DateTimeParseException | NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        request.setAttribute("schedule", schedule);
        getServletContext().getRequestDispatcher("/student_schedule.jsp").forward(request, response);
    }
}
