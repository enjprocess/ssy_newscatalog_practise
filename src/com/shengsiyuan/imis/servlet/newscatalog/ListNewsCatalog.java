package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.Page;
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        long start = ParamUtils.getParameter(request, "start", 0);
        long range = ParamUtils.getParameter(request, "range", 10);
        
        
        NewsCatalogService service = new NewsCatalogServiceImpl();
        try {
            long total = service.getNewsCatalogCount(parentId);
            List<NewsCatalog> list = service.listNewsCatalogByParentId(parentId, start, range);
            if (-1 != parentId) {
                NewsCatalog bean = service.listNewsCatalogById(parentId);
                long upperId = bean.getParentId();
                request.setAttribute("upperId", String.valueOf(upperId));
            }
            String pageLink = Page.pageLink(request, "", start, range, total);
            System.out.println(pageLink);
            request.setAttribute("pageLink", pageLink);
            request.setAttribute("list", list);
            request.setAttribute("parentId", String.valueOf(parentId));
            request.getRequestDispatcher("listNewsCatalog.jsp").forward(request, response);
            return;
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
