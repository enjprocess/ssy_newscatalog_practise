package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.NewsAttachment;

public interface NewsAttachmentDao {

    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId) throws DaoException;
 
    public void deleteNewsAttachmentById(long id) throws DaoException;
    
    public void addNewsAttachment(NewsAttachment bean) throws DaoException;
    
    public long getNewsAttachmentCount(long parentId) throws DaoException;
}
