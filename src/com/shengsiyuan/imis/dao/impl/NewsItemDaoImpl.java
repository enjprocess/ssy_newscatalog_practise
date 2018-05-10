package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsItemDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsItem;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsItemDaoImpl extends AbstractBaseDao implements NewsItemDao {

    public NewsItemDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<NewsItem> listNewsItemByParentId(long parentId, long start,
            long range) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where parentId = ? order by id desc limit ?,? ");
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ps.setLong(2, start);
            ps.setLong(3, range);
            ResultSet rs = ps.executeQuery();
            List<NewsItem> list = new ArrayList<NewsItem>();
            while (rs.next()) {
                NewsItem item = new NewsItem();
                String title = rs.getString("title");
                String content = rs.getString("content");
                long id = rs.getLong("id");
                item.setTitle(title);
                item.setContent(content);
                item.setId(id);
                item.setParentId(parentId);
                list.add(item);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
       
    }

    @Override
    public void updateNewsItem(NewsItem bean) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" set title = ?, content= ? where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getTitle());
            ps.setString(2, bean.getContent());
            ps.setLong(3, bean.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void deleteNewsItemById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void addNewsItem(NewsItem bean) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append("(parentId, title, content) values(?, ?, ?)");
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, bean.getParentId());
            ps.setString(2, bean.getTitle());
            ps.setString(3, bean.getContent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public NewsItem listNewsItemById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsItem bean = new NewsItem();
                String title = rs.getString("title");
                String content = rs.getString("content");
                long parentId = rs.getLong("parentId");
                bean.setId(rs.getLong("id"));
                bean.setTitle(title);
                bean.setContent(content);
                bean.setParentId(parentId);
                return bean;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
        return null;
    }

    @Override
    public long getNewsItemCount(long parentId) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) total from ").append(DaoConstants.NEWSITEM_TABLE_NAME)
                .append(" where parentId = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
        return 0;
    }

}
