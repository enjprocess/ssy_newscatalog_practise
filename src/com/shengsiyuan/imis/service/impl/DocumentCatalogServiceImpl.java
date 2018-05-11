package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.DocumentCatalogDao;
import com.shengsiyuan.imis.dao.impl.DocumentCatalogDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.service.DocumentCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;

public class DocumentCatalogServiceImpl extends AbstractBaseService implements DocumentCatalogService {

    @Override
    public List<DocumentCatalog> listDocumentCatalogByParentIdAndType(long parentId, long type)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(tc.getConnection());
        try {
            List<DocumentCatalog> list = dao.listDocumentCatalogByParentIdAndType(parentId, type);
            transManager.commitTransaction(tc);
            return list;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_DOCUMENTCATALOG_ERROR);
        }
    }

    @Override
    public void updateDocumentCatalog(DocumentCatalog bean)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(tc.getConnection());
        try {
            dao.updateDocumentCatalog(bean);
            transManager.commitTransaction(tc);
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.UPDATE_DOCUMENTCATALOG_ERROR);
        }
    }

    @Override
    public long addDocumentCatalog(DocumentCatalog bean)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(tc.getConnection());
        try {
            long id = dao.addDocumentCatalog(bean);
            //更新父节点状态
            if (-1 != bean.getParentId()) {
                DocumentCatalog doc = dao.getDocumentCatalogById(bean.getParentId());
                doc.setState("closed");
                dao.updateDocumentCatalog(doc);
            }
            transManager.commitTransaction(tc);
            return id;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.ADD_DOCUMENTCATALOG_ERROR);
        }
    }

    @Override
    public DocumentCatalog getDocumentCatalogById(long id)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        DocumentCatalogDao dao = new DocumentCatalogDaoImpl(tc.getConnection());
        try {
            DocumentCatalog bean = dao.getDocumentCatalogById(id);
            transManager.commitTransaction(tc);
            return bean;
        } catch (DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.UPDATE_DOCUMENTCATALOG_ERROR);
        }
    }

}
