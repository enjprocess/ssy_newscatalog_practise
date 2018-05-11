package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogDao {

    public List<DocumentCatalog> listDocumentCatalogByParentIdAndType(long parentId, long type) throws DaoException;
 
    public void updateDocumentCatalog(DocumentCatalog bean) throws DaoException;
    
    public long addDocumentCatalog(DocumentCatalog bean) throws DaoException;
    
    public DocumentCatalog getDocumentCatalogById(long id) throws DaoException;
}
