package com.student.servlets;

import com.student.dao.Student;
import com.student.repositories.StudentRepositories;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    StudentRepositories studentRepositories = new StudentRepositories();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("student_name");
        int age = Integer.parseInt(request.getParameter("student_age"));
        String course = request.getParameter("student_course");

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setCourse(course);

        try {
            studentRepositories.registerStudent(student);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("add.html");
        requestDispatcher.forward(request, response);
    }
}
