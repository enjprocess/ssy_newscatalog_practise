package com.shengsiyuan.imis.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardTest extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.service(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        Random rand = new Random();
//        int v = rand.nextInt(3);
//        switch (v) {
//        case 0:
//            request.getRequestDispatcher("/a.jsp").forward(request, response);
//            break;
//        case 1:
//            request.getRequestDispatcher("/b.jsp").forward(request, response);
//            break;
//        case 2:
//            request.getRequestDispatcher("/c.jsp").forward(request, response);
//            break;
//        }
        response.sendRedirect("../a.jsp"); 
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost");
        doGet(request, response);
    }

}
