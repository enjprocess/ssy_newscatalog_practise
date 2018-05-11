package com.shengsiyuan.imis.servlet.documentcatalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class AddDocumentCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        long parentId = ParamUtils.getParameter(request, "parentId");
        long type = ParamUtils.getParameter(request, "type");
        
        DocumentCatalog bean = new DocumentCatalog();
        bean.setName(title);
        bean.setParentId(parentId);
        bean.setType(type);
        bean.setState("leaf");
        
        DocumentCatalogService service = new DocumentCatalogServiceImpl();
        try {
            long id = service.addDocumentCatalog(bean);
            response.setContentType("application/json;charset=utf-8");
            out.println(id);
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
