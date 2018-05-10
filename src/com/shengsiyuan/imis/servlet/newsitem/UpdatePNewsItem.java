package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class UpdatePNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getParameter(request, "parentId");
        long start = ParamUtils.getParameter(request, "start");
        long range = ParamUtils.getParameter(request, "range");
        long id = ParamUtils.getParameter(request, "id");
        
        NewsItemService service = new NewsItemServiceImpl();
        try {
            NewsItem bean = service.listNewsItemById(id);
            request.setAttribute("bean", bean);
            request.setAttribute("parentId", parentId);
            request.setAttribute("start", start);
            request.setAttribute("range", range);
            request.getRequestDispatcher("updateNewsItem.jsp").forward(request, response);
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
