package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsCatalog;

public interface NewsCatalogDao {
    
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId, long start, long range) throws DaoException;
    
    public long getNewsCatalogCount(long parentId) throws DaoException;

    public List<NewsCatalog> listAllNewsCatalogByParentId(long parentId) throws DaoException;
    
    public NewsCatalog listNewsCatalogById(long id) throws DaoException;
    
    public NewsCatalog listNewsCatalogByNameAndParentId(String name, long parentId) throws DaoException;
    
    public void addNewsCatalog(NewsCatalog bean) throws DaoException;
    
    public void updateNewsCatalog(NewsCatalog bean) throws DaoException;
    
    public void deleteNewsCatalogById(long id) throws DaoException;
    
}
