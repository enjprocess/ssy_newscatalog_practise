package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shengsiyuan.imis.dao.NewsCatalogDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.model.NewsCatalog;
import com.shengsiyuan.imis.model.Test;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class NewsCatalogDaoImpl extends AbstractBaseDao implements NewsCatalogDao {

    public NewsCatalogDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<NewsCatalog> listNewsCatalogByParentId(long parentId, long start, long range)
            throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where parentId = ? order by id desc limit ?, ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, parentId);
            ps.setLong(2, start);
            ps.setLong(3, range);
            ResultSet rs = ps.executeQuery();
            List<NewsCatalog> list = new ArrayList<NewsCatalog>();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                long id = rs.getLong("id");
                String name = rs.getString("name");
                bean.setId(id);
                bean.setName(name);
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public void addNewsCatalog(NewsCatalog bean) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append("(name, parentId) values(?, ?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getParentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public NewsCatalog listNewsCatalogById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                String name = rs.getString("name");
                long parentId = rs.getLong("parentId");
                bean.setId(rs.getLong("id"));
                bean.setName(name);
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
    public NewsCatalog listNewsCatalogByNameAndParentId(String name,
            long parentId) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where name = ? and parentId = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, name);
            ps.setLong(2, parentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                long id = rs.getLong("id");
                bean.setId(id);
                bean.setName(rs.getString("name"));
                bean.setParentId(rs.getLong("parentId"));
                return bean;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
        return null;
    }

    @Override
    public void updateNewsCatalog(NewsCatalog bean) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" set name = ?, parentId = ? where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getParentId());
            ps.setLong(3, bean.getId());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public List<NewsCatalog> listAllNewsCatalogByParentId(long parentId)
            throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
                .append(" where parentId = ? order by id desc");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, parentId);
            ResultSet rs = ps.executeQuery();
            List<NewsCatalog> list = new ArrayList<NewsCatalog>();
            while (rs.next()) {
                NewsCatalog bean = new NewsCatalog();
                long id = rs.getLong("id");
                String name = rs.getString("name");
                bean.setId(id);
                bean.setName(name);
                bean.setParentId(rs.getLong("parentId"));
                list.add(bean);
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public long getNewsCatalogCount(long parentId) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) total from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
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

    @Override
    public void deleteNewsCatalogById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from ").append(DaoConstants.NEWSCATALOG_TABLE_NAME)
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

}
