package com.shengsiyuan.imis.servlet.newsattachment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.service.impl.NewsAttachmentServiceImpl;

public class AddNewsAttachment extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 使用多文件上传时，也是一个文件一个请求的,应该是通过flash的

        // 首先创建一个工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 创建一个文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析请求
        try {
            // 通过ServletFileUpload来解析request请求
            List<FileItem> list = upload.parseRequest(request);
            FileItem fileItem = null;
            String parentId = "";
            if (list != null) {
                for (FileItem item : list) {
                    // 如果是上传空间那么进行赋值操作
                    if (!item.isFormField()) {
                        fileItem = item;
                    } else {// 如果是表单控件那么进行如下处理
                        String fieldName = item.getFieldName();
                        String value = item.getString();
                        if ("parentId".equals(fieldName)) {
                            parentId = value;
                        }
                    }
                }

                // 处理文件区域
                if (null != fileItem) {
                    // 获得原始文件名
                    String fileName = fileItem.getName();
                    // 获得扩展名
                    String ext = fileName.substring(fileName.lastIndexOf("."));
                    // 通过Commons-lang下的工具类轻松获得5位随机文件名
                    String randomName = RandomStringUtils.randomAlphanumeric(5);
                    File dir = new File(request.getSession()
                            .getServletContext().getRealPath("/filestorage"),
                            parentId);
                    // 创建文件夹，以parentId为基准
                    dir.mkdir();
                    String newName = randomName + ext;
                    File resultFile = new File(dir, newName);
                    // 使用fileItem的方法将上传文件写出到指定的磁盘上
                    fileItem.write(resultFile);

                    // 写入数据库
                    NewsAttachmentService service = new NewsAttachmentServiceImpl();
                    NewsAttachment bean = new NewsAttachment();
                    bean.setOriginName(fileName);
                    bean.setNewName(newName);
                    bean.setParentId(Long.parseLong(parentId));
                    service.addNewsAttachment(bean);
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
