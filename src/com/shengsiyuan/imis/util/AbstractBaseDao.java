package com.shengsiyuan.imis.util;

import java.sql.Connection;

public class AbstractBaseDao {

    protected Connection conn;
    
    public AbstractBaseDao(Connection conn) {
        this.conn = conn;
    }
    
}
