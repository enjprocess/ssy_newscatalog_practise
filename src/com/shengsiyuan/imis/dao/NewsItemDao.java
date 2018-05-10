package com.shengsiyuan.imis.dao;

import java.util.List;

import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.NewsItem;

public interface NewsItemDao {

    public List<NewsItem> listNewsItemByParentId(long parentId, long start, long range) throws DaoException;
 
    public void updateNewsItem(NewsItem bean) throws DaoException;
    
    public void deleteNewsItemById(long id) throws DaoException;
    
    public void addNewsItem(NewsItem bean) throws DaoException;
    
    public NewsItem listNewsItemById(long id) throws DaoException;
    
    public long getNewsItemCount(long parentId) throws DaoException;
}
