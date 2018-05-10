package com.shengsiyuan.imis.service;

import java.util.List;

import com.shengsiyuan.imis.exception.ServiceException;
import com.shengsiyuan.imis.model.NewsItem;

public interface NewsItemService {

    public List<NewsItem> listNewsItemByParentId(long parentId, long start, long range) throws ServiceException;
    
    public long getNewsItemCount(long parentId) throws ServiceException;
    
    public NewsItem listNewsItemById(long id) throws ServiceException;
    
    //public boolean hasNewsItemExists(String name, long parentId) throws ServiceException;
    
    public void addNewsItem(NewsItem bean) throws ServiceException;

    public void updateNewsItem(NewsItem bean) throws ServiceException;
    
    public void deleteNewsItemById(long id) throws ServiceException;
}
