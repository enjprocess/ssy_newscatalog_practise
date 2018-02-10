package com.shengsiyuan.imis.service.impl;

import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.dao.impl.NewsCatalogDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.service.NewsCatalogService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsCatalogServiceImpl extends AbstractBaseService implements
        NewsCatalogService {

    @Override
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            List<NewsCatalog> list = dao.listNewsCatalogByParentId(parentId);
            tc.commitTransaction();
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void addNewsCatalog(NewsCatalog bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            dao.addNewsCatalog(bean);
            tc.commitTransaction();
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
            throw new ServiceException(ErrorCode.ADD_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public NewsCatalog listNewsCatalogById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            NewsCatalog bean = dao.listNewsCatalogById(id);
            tc.commitTransaction();
            return bean;
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
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
            tc.commitTransaction();
            if (null != bean) {
                return true;
            } else {
                return false;
            }
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void updateNewsCatalog(NewsCatalog bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsCatalogDao dao = new NewsCatalogDaoImpl(tc.getConnection());
            dao.updateNewsCatalog(bean);
            tc.commitTransaction();
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
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
            List<NewsCatalog> list = dao.listNewsCatalogByParentId(bean.getParentId());
            tc.commitTransaction();
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            tc.rollbackTransaction();
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

}
