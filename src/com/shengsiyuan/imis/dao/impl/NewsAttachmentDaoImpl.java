package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsAttachmentDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsAttachmentDaoImpl extends AbstractBaseDao implements NewsAttachmentDao {

    public NewsAttachmentDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<NewsAttachment> listNewsAttachmentByParentId(long parentId) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ").append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
                .append(" where parentId = ? order by id desc");
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sb.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            List<NewsAttachment> list = new ArrayList<NewsAttachment>();
            while (rs.next()) {
                NewsAttachment item = new NewsAttachment();
                String originName = rs.getString("originName");
                String newName = rs.getString("newName");
                long id = rs.getLong("id");
                item.setOriginName(originName);
                item.setNewName(newName);
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

    public void deleteNewsAttachmentById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from ").append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
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
    public void addNewsAttachment(NewsAttachment bean) throws DaoException {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
                .append("(originName, newName, parentId) values(?, ?, ?)");
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, bean.getOriginName());
            ps.setString(2, bean.getNewName());
            ps.setLong(3, bean.getParentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public long getNewsAttachmentCount(long parentId) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) total from ").append(DaoConstants.NEWSATTACHMENT_TABLE_NAME)
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
