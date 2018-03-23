package com.shengsiyuan.imis.servlet.newscatalog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.impl.NewsCatalogServiceImpl;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.util.MessageHelper;
import com.shengsiyuan.imis.util.ParamUtils;
import com.shengsiyuan.imis.util.ServletString;

public class UpdateNewsCatalog extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        NewsCatalog bean = new NewsCatalog();
        String name = request.getParameter("name");
        long id = ParamUtils.getParameter(request, "id");
        long oldParentId = ParamUtils.getParameter(request, "oldParentId", -1);
        long newParentId = ParamUtils.getParameter(request, "newParentId", -1);
        long start = ParamUtils.getParameter(request, "start", 0);
        long range= ParamUtils.getParameter(request, "range", 10);
        bean.setId(id);
        bean.setName(name);
        bean.setParentId(newParentId);
        
        NewsCatalogService service = new NewsCatalogServiceImpl();

        try {
            boolean exists = service.hasNewsCatalogExists(name, newParentId);
            //跳转到错误统一页面
            if (exists) {
                request.setAttribute(ServletString.GLOBAL_ERROR_NAME, MessageHelper.getExceptionInfo(ErrorCode.REPEAT_NEWSCATALOG_ERROR));
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
            service.updateNewsCatalog(bean);
            response.sendRedirect("ListNewsCatalog?parentId=" + oldParentId + "&start=" + start + "&range=" + range);
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
