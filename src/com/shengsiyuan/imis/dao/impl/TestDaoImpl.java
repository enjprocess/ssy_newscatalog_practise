package com.shengsiyuan.imis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shengsiyuan.imis.dao.TestDao;
import com.shengsiyuan.imis.exception.DaoException;
import com.shengsiyuan.imis.model.Test;
import com.shengsiyuan.imis.util.AbstractBaseDao;
import com.shengsiyuan.imis.util.DaoConstants;

public class TestDaoImpl extends AbstractBaseDao implements TestDao {

    public TestDaoImpl(Connection conn) {
        super(conn);
    }
    
    @Override
    public Test getTestById(long id) throws DaoException {

        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(DaoConstants.TEST_TABLE_NAME)
                .append(" where id = ?");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Test bean = new Test();
                String name = rs.getString("name");
                long age = rs.getLong("age");
                bean.setName(name);
                bean.setAge(age);
                bean.setId(rs.getLong("id"));
                return bean;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return null;
    }

}
