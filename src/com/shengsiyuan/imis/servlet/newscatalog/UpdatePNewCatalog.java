package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;
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

public class UpdatePNewCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        long id = ParamUtils.getParameter(request, "id");

        NewsCatalogService service = new NewsCatalogServiceImpl();

        try {
            NewsCatalog bean = service.listNewsCatalogById(id);
            long parentId = bean.getParentId();
            if (-1 != parentId) {
                List<NewsCatalog> list = service.listParentSiblingsByParentId(parentId);
                request.setAttribute("list", list);
            }
            
            request.setAttribute("bean", bean);
            request.getRequestDispatcher("UpdateNewsCatalog.jsp").forward(
                    request, response);
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
