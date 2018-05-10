package com.shengsiyuan.imis.servlet.newsitem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.model.NewsItemAndNewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.service.impl.NewsAttachmentServiceImpl;
import com.shengsiyuan.imis.service.impl.NewsItemServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class ViewNewsItem extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        long id = ParamUtils.getParameter(request, "id");

        NewsItemService service = new NewsItemServiceImpl();
        
        NewsAttachmentService attachmentService = new NewsAttachmentServiceImpl();
        try {
            NewsItem bean = service.listNewsItemById(id);
            
            List<NewsAttachment> list = attachmentService.listNewsAttachmentByParentId(id);
            
            NewsItemAndNewsAttachment result = new NewsItemAndNewsAttachment();
            result.setNewsName(bean.getTitle());
            result.setNewsContent(bean.getContent());
            result.setList(list);
            
            Gson gson = new Gson();
            String json = gson.toJson(result);
            System.out.println(json);
            out.write(json);
        } catch (ServiceException e1) {
        }
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
