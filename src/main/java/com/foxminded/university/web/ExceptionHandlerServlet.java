package com.foxminded.university.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exception_handler")
public class ExceptionHandlerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            getServletContext().getRequestDispatcher("/404error.jsp").forward(request, response);
        }
        
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        
        if (throwable.getClass() == java.lang.NumberFormatException.class
                | throwable.getClass() == java.time.DateTimeException.class) {
            StringBuilder message = new StringBuilder("incorrect input" + throwable.getMessage());
            request.setAttribute("message", message.toString());
            getServletContext().getRequestDispatcher("/input_exception.jsp").forward(request, response);
        } else {
            request.setAttribute("message", throwable);
            getServletContext().getRequestDispatcher("/exception.jsp").forward(request, response);
        }
        
    }
    
}
