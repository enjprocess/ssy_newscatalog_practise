package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class DeleteNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long id = ParamUtils.getParameter(request, "id");
        long start = ParamUtils.getParameter(request, "start");
        long range = ParamUtils.getParameter(request, "range");
        long parentId = ParamUtils.getParameter(request, "parentId");
        
        NewsItemService service = new NewsItemServiceImpl();
        try {
            service.deleteNewsItemById(id);
            response.sendRedirect("ListNewsItem?parentId=" + parentId + "&start=" + start + "&range=" + range);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
