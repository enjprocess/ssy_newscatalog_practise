package com.shengsiyuan.imis.servlet.newsattachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shengsiyuan.imis.util.ParamUtils;

public class DownloadNewsAttachment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        // 下载思路
        // 并不是直接访问资源，而是通过servlet来控制下载
        long parentId = ParamUtils.getParameter(request, "parentId");
        String name = request.getParameter("name");
        String randomName = request.getParameter("randomName");

        ServletOutputStream os = response.getOutputStream();
        // 将文件读入到内存中
        FileInputStream in = new FileInputStream(new File(getServletContext()
                .getRealPath("/filestorage/" + parentId), randomName));

        //设置http响应消息头的信息
        response.setHeader("Content-Disposition", "attachment;filename=\""
                + URLEncoder.encode(name, "UTF-8") + "\"");

        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = in.read(buff)) != -1) {
            os.write(buff, 0, len);
        }

        in.close();
        os.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
