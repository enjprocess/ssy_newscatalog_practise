package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;

public interface NewsCatalogService {

    public List<NewsCatalog> listNewsCatalogByParentId(long parentId, long start, long range) throws ServiceException;
    
    public long getNewsCatalogCount(long parentId) throws ServiceException;
    
    public NewsCatalog listNewsCatalogById(long id) throws ServiceException;
    
    public boolean hasNewsCatalogExists(String name, long parentId) throws ServiceException;
    
    public void addNewsCatalog(NewsCatalog bean) throws ServiceException;

    public List<NewsCatalog> listParentSiblingsByParentId(long parentId) throws ServiceException;
    
    public void updateNewsCatalog(NewsCatalog bean) throws ServiceException;
}
