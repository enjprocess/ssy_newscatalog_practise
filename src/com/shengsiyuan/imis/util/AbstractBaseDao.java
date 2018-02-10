package com.shengsiyuan.imis.util;

import java.sql.Connection;

public class AbstractBaseDao implements Dao {

    protected Connection conn;
    
    public AbstractBaseDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }
    
}
