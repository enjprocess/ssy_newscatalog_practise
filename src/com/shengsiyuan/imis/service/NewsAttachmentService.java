package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsAttachment;

public interface NewsAttachmentService {

    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId) throws ServiceException;
    
    public long getNewsAttachmentCount(long parentId) throws ServiceException;
    
    //public boolean hasNewsAttachmentExists(String name, long parentId) throws ServiceException;
    
    public void addNewsAttachment(NewsAttachment bean) throws ServiceException;

    public void deleteNewsAttachmentById(long id) throws ServiceException;
}
