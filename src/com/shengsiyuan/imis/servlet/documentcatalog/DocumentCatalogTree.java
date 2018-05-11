package com.shengsiyuan.imis.servlet.documentcatalog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DataObj;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.model.Tree;
import com.shengsiyuan.imis.model.TreeAttr;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.service.impl.DocumentCatalogServiceImpl;
import com.shengsiyuan.imis.util.ParamUtils;

public class DocumentCatalogTree extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        
        long parentId = ParamUtils.getParameter(request, "parentId", -1);
        long type = ParamUtils.getParameter(request, "type");

        DocumentCatalogService service = new DocumentCatalogServiceImpl();
        try {
            List<DocumentCatalog> list = service.listDocumentCatalogByParentIdAndType(parentId, type);
            List<Tree> treeList = new ArrayList<Tree>();
            for (DocumentCatalog doc : list) {
                Tree tree = new Tree();
                DataObj data = new DataObj();
                //设置节点名称
                data.setTitle(doc.getName());
                TreeAttr attr = new TreeAttr();
                attr.setId("node" + doc.getId());
                //设置节点属性
                data.setAttributes(attr);
                //设置节点状态
                tree.setState(doc.getState());
                tree.setData(data);
                treeList.add(tree);
            }
            Gson gson = new Gson();
            String json = gson.toJson(treeList);
            response.setContentType("application/json; charset=utf-8");
            out.write(json);
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
