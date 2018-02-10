package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.Test;
import com.shengsiyuan.imis.service.TestService;
import com.shengsiyuan.imis.service.impl.TestServiceImpl;

public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        TestService ts = new TestServiceImpl();
        try {
            Test bean = ts.getTestById(1);
            System.out.println(bean.getName());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
