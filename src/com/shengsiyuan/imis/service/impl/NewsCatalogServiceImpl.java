package com.shengsiyuan.imis.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.dao.impl.NewsCatalogDaoImpl;
import com.shengsiyuan.imis.exception.ConnectionException;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.DBConnectionFactory;
import com.shengsiyuan.imis.util.TransactionContext;
import com.shengsiyuan.imis.util.TransactionManager;

public class NewsCatalogServiceImpl extends AbstractBaseService implements
        NewsCatalogService {

    @Override
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId, long start, long range)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            List<NewsCatalog> list = dao.listNewsCatalogByParentId(parentId, start, range);
            transManager.commitTransaction(tc);
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void addNewsCatalog(NewsCatalog bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            dao.addNewsCatalog(bean);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.ADD_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public NewsCatalog listNewsCatalogById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            NewsCatalog bean = dao.listNewsCatalogById(id);
            transManager.commitTransaction(tc);
            return bean;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public boolean hasNewsCatalogExists(String name, long parentId)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            NewsCatalog bean = dao.listNewsCatalogByNameAndParentId(name, parentId);
            transManager.commitTransaction(tc);
            if (null != bean) {
                return true;
            } else {
                return false;
            }
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void updateNewsCatalog(NewsCatalog bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            dao.updateNewsCatalog(bean);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.UPDATE_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public List<NewsCatalog> listParentSiblingsByParentId(long parentId)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            NewsCatalog bean = dao.listNewsCatalogById(parentId);
            List<NewsCatalog> list = dao.listAllNewsCatalogByParentId(bean.getParentId());
            transManager.commitTransaction(tc);
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public long getNewsCatalogCount(long parentId) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            long total = dao.getNewsCatalogCount(parentId);
            transManager.commitTransaction(tc);
            return total;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void deleteNewsCatalogById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            dao.deleteNewsCatalogById(id);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.DELETE_NEWSCATALOG_ERROR, e);
        }
    }

}
