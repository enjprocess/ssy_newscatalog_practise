package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.shengsiyuan.imis.dao.DocumentCatalogDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.exception.ErrorCode;
import com.shengsiyuan.imis.model.DocumentCatalog;
import com.shengsiyuan.imis.model.NewsAttachment;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class DocumentCatalogDaoImpl extends AbstractBaseDao implements
        DocumentCatalogDao {

    public DocumentCatalogDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public void updateDocumentCatalog(DocumentCatalog bean) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" set name = ?, state = ? where id = ?");
        System.out.println(sql.toString());
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getState());
            ps.setLong(3, bean.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public long addDocumentCatalog(DocumentCatalog bean) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append("(parentId, state, name, type) values(?, ?, ?, ?)");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, bean.getParentId());
            ps.setString(2, bean.getState());
            ps.setString(3, bean.getName());
            ps.setLong(4, bean.getType());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            while (keys.next()) {
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
        
        return -1;
    }
    
    
    
    
    

    @Override
    public List<DocumentCatalog> listDocumentCatalogByParentIdAndType(
            long parentId, long type) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" where parentId = ? and type = ? order by id desc");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, parentId);
            ps.setLong(2, type);
            ResultSet rs = ps.executeQuery();
            List<DocumentCatalog> list = new ArrayList<DocumentCatalog>();
            while (rs.next()) {
                DocumentCatalog dc = new DocumentCatalog();
                long id = rs.getLong("id");
                String state = rs.getString("state");
                String name = rs.getString("name");
                
                dc.setId(id);
                dc.setState(state);
                dc.setName(name);
                dc.setParentId(parentId);
                dc.setType(type);
                list.add(dc);
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
    }

    @Override
    public DocumentCatalog getDocumentCatalogById(long id) throws DaoException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ")
                .append(DaoConstants.DOCUMENTCATALOG_TABLE_NAME)
                .append(" where id = ?");
        DocumentCatalog dc = new DocumentCatalog();
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                String state = rs.getString("state");
                String name = rs.getString("name");
                long parentId = rs.getLong("parentId");
                long type = rs.getLong("type");
                
                dc.setId(id);
                dc.setState(state);
                dc.setName(name);
                dc.setType(type);
                dc.setParentId(parentId);
            }
        } catch (SQLException e) {
            throw new DaoException(ErrorCode.SQL_ERROR);
        }
        return dc;
    }

}
