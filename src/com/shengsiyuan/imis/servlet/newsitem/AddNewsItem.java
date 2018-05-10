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

public class AddNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        NewsItem bean = new NewsItem();
        String title = request.getParameter("name");
        String content = request.getParameter("content");
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        
        bean.setTitle(title);
        bean.setContent(content);
        bean.setParentId(parentId);
        
        NewsItemService service = new NewsItemServiceImpl();
        try {
            service.addNewsItem(bean);
            response.sendRedirect("ListNewsItem?parentId=" + parentId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
