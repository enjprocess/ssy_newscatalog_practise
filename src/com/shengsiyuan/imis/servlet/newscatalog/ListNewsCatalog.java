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
import com.shengsiyuan.imis.util.ParamUtils;

public class ListNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        
        NewsCatalogService service = new NewsCatalogServiceImpl();
        try {
            List<NewsCatalog> list = service.listNewsCatalogByParentId(parentId);
            if (-1 != parentId) {
                NewsCatalog bean = service.listNewsCatalogById(parentId);
                long upperId = bean.getParentId();
                request.setAttribute("upperId", String.valueOf(upperId));
            }
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
