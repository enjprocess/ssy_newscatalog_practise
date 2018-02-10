package com.shengsiyuan.imis.service;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.Test;

public interface TestService {

    Test getTestById(long id) throws ServiceException;
}
