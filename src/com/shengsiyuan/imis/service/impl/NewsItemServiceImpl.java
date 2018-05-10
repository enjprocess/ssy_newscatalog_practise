package com.shengsiyuan.imis.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsItemDao;
import com.shengsiyuan.imis.dao.impl.NewsItemDaoImpl;
import com.shengsiyuan.imis.exception.ConnectionException;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.service.NewsItemService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.DBConnectionFactory;
import com.shengsiyuan.imis.util.TransactionContext;

public class NewsItemServiceImpl extends AbstractBaseService implements
        NewsItemService {

    @Override
    public List<NewsItem> listNewsItemByParentId(long parentId, long start, long range)
            throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            List<NewsItem> list = dao.listNewsItemByParentId(parentId, start, range);
            transManager.commitTransaction(tc);
            return list;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void addNewsItem(NewsItem bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            dao.addNewsItem(bean);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.ADD_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public NewsItem listNewsItemById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            NewsItem bean = dao.listNewsItemById(id);
            transManager.commitTransaction(tc);
            return bean;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }


    @Override
    public void updateNewsItem(NewsItem bean) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            dao.updateNewsItem(bean);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.UPDATE_NEWSCATALOG_ERROR, e);
        }
    }

  

    @Override
    public long getNewsItemCount(long parentId) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            long total = dao.getNewsItemCount(parentId);
            transManager.commitTransaction(tc);
            return total;
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.LIST_NEWSCATALOG_ERROR, e);
        }
    }

    @Override
    public void deleteNewsItemById(long id) throws ServiceException {
        TransactionContext tc = transManager.beginTransaction();
        try {
            NewsItemDao dao = new NewsItemDaoImpl(tc.getConnection());
            dao.deleteNewsItemById(id);
            transManager.commitTransaction(tc);
        } catch(DaoException e) {
            e.printStackTrace();
            transManager.rollbackTransaction(tc);
            throw new ServiceException(ErrorCode.DELETE_NEWSCATALOG_ERROR, e);
        }
    }

}
