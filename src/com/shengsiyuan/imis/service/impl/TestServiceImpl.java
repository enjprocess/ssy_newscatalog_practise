package com.shengsiyuan.imis.service.impl;

import com.shengsiyuan.imis.dao.TestDao;
import com.shengsiyuan.imis.dao.impl.TestDaoImpl;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.Test;
import com.shengsiyuan.imis.service.TestService;
import com.shengsiyuan.imis.util.AbstractBaseService;
import com.shengsiyuan.imis.util.DaoConstants;
import com.shengsiyuan.imis.util.TransactionContext;

public class TestServiceImpl extends AbstractBaseService implements TestService {

    @Override
    public Test getTestById(long id) throws ServiceException {
        
        TransactionContext tc = transManager.beginTransaction();
        try {
            TestDao dao = new TestDaoImpl(tc.getConnection());
            Test bean = dao.getTestById(id);
            transManager.commitTransaction(tc);
            return bean;
        } catch (DaoException e) {
            transManager.rollbackTransaction(tc);
            throw new ServiceException(e);
        }
    }

}
