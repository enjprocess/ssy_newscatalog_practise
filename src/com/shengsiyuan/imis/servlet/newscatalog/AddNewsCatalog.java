package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.ParamSupport;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class AddNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        NewsCatalog bean = new NewsCatalog();
        String name = request.getParameter("name");
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        bean.setName(name);
        bean.setParentId(parentId);
        NewsCatalogService service = new NewsCatalogServiceImpl();
        try {
            service.addNewsCatalog(bean);
            response.sendRedirect("ListNewsCatalog?parentId=" + parentId);
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
