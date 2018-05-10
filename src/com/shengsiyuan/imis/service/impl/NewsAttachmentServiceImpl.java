package com.shengsiyuan.imis.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsAttachmentDao;
import com.shengsiyuan.imis.dao.impl.NewsAttachmentDaoImpl;
import com.shengsiyuan.imis.exception.ConnectionException;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.service.NewsAttachmentService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.DBConnectionFactory;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsAttachmentServiceImpl extends AbstractBaseService implements
        NewsAttachmentService {

    @Override
    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsAttachmentDao dao = new NewsAttachmentDaoImpl(tc.getConnection());
            List<NewsAttachment> list = dao.listNewsAttachmentByParentId(parentId);
            transManager.commitTransaction(tc);
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSATTACHMENT_ERROR, e);
        }
    }

    @Override
    public void addNewsAttachment(NewsAttachment bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsAttachmentDao dao = new NewsAttachmentDaoImpl(tc.getConnection());
            dao.addNewsAttachment(bean);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.ADD_NEWSATTACHMENT_ERROR, e);
        }
    }

    @Override
    public long getNewsAttachmentCount(long parentId) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsAttachmentDao dao = new NewsAttachmentDaoImpl(tc.getConnection());
            long total = dao.getNewsAttachmentCount(parentId);
            transManager.commitTransaction(tc);
            return total;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSATTACHMENT_ERROR, e);
        }
    }

    @Override
    public void deleteNewsAttachmentById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsAttachmentDao dao = new NewsAttachmentDaoImpl(tc.getConnection());
            dao.deleteNewsAttachmentById(id);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.DELETE_NEWSATTACHMENT_ERROR, e);
        }
    }

}
