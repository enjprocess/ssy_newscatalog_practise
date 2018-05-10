package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.Page;
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        long parentId = ParamUtils.getParameter(request, "parentId");
        long start = ParamUtils.getParameter(request, "start", 0);
        long range = ParamUtils.getParameter(request, "range", 10);
        

        NewsItemService service = new NewsItemServiceImpl();
        try {
            List<NewsItem> list = service.listNewsItemByParentId(parentId, start, range);
            long totalNums = service.getNewsItemCount(parentId);
            String pageLink = Page.pageLink(request, "parentId=" + parentId, start, range, totalNums);
            System.out.println("NewsItem pagelink: " + pageLink);
            request.setAttribute("list", list);
            request.setAttribute("pageLink", pageLink);
            request.setAttribute("start", String.valueOf(start));
            request.setAttribute("range", String.valueOf(range));
            request.setAttribute("parentId", String.valueOf(parentId));
            request.getRequestDispatcher("listNewsItem.jsp").forward(request, response);
            return;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
