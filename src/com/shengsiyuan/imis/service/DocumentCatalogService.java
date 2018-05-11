package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.DocumentCatalog;

public interface DocumentCatalogService {

    public List<DocumentCatalog> listDocumentCatalogByParentIdAndType(long parentId, long type) throws ServiceException;
    
    public void updateDocumentCatalog(DocumentCatalog bean) throws ServiceException;
    
    public long addDocumentCatalog(DocumentCatalog bean) throws ServiceException;
    
    public DocumentCatalog getDocumentCatalogById(long id) throws ServiceException;
}
