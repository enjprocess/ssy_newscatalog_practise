package com.shengsiyuan.imis.dao;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.Test;

public interface TestDao {

    public Test getTestById(long id) throws DaoException;
}
