package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;
import com.shengsiyuan.imis.util.ServletString;

public class DeleteNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long id = ParamUtils.getParameter(request, "id");
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        long start = ParamUtils.getParameter(request, "start", 0);
        long range = ParamUtils.getParameter(request, "range", 10);
        
        NewsCatalogService service = new NewsCatalogServiceImpl();
        try {
            long newsCatalogNum = service.getNewsCatalogCount(id);
            if (0 != newsCatalogNum) {
                request.setAttribute(ServletString.GLOBAL_ERROR_NAME, "有子分类不能够删除!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else {
                service.deleteNewsCatalogById(id);
                response.sendRedirect("ListNewsCatalog?parentId=" + parentId + "&start=" + start + "&range=" + range);
            }
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
