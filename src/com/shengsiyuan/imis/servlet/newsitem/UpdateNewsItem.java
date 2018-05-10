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

public class UpdateNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long parentId = ParamUtils.getParameter(request, "parentId");
        long start = ParamUtils.getParameter(request, "start");
        long range = ParamUtils.getParameter(request, "range");
        long id = ParamUtils.getParameter(request, "id");

        String name = request.getParameter("name");
        String content = request.getParameter("content");

        NewsItem bean = new NewsItem();
        bean.setTitle(name);
        bean.setContent(content);
        bean.setId(id);

        NewsItemService service = new NewsItemServiceImpl();
        try {
            service.updateNewsItem(bean);
            response.sendRedirect("ListNewsItem?parentId=" + parentId
                    + "&start=" + start + "&range=" + range);
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
