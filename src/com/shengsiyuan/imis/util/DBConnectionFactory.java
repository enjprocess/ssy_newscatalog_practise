package com.shengsiyuan.imis.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.shengsiyuan.imis.exception.ConnectionException;
import com.shengsiyuan.imis.exception.ErrorCode;

/**
 * 取数据库连接的类,单例
 * <p>Title: DBConnectionFactory</p>
 * <p>Description: </p>
 * <p>Company: 自由职业</p> 
 * @author lsw
 * @date 2018年2月2日
 */
public class DBConnectionFactory {
    
    private DataSource dataSource;
    
    public static DBConnectionFactory getInstance = new DBConnectionFactory();
    
    private DBConnectionFactory() {
        buildConnectionPool();
    }
    
    public void buildConnectionPool() {
        BasicDataSource db = new BasicDataSource();
        Properties prop;
        try {
            prop = ConfigHelper.getProperties(FileNameString.CONFIG_FILE_NAME);
            db.setDriverClassName(prop.getProperty(DBString.JDBC_DRIVER));
            db.setUrl(prop.getProperty(DBString.JDBC_URL));
            db.setUsername(prop.getProperty(DBString.JDBC_USERNAME));
            db.setPassword(prop.getProperty(DBString.JDBC_PASSWORD));
            int initialSize = Integer.parseInt(prop.getProperty(DBString.JDBC_MIN_CONNECTIONS));
            int maxActive = Integer.parseInt(prop.getProperty(DBString.JDBC_MAX_CONNECTIONS));
            db.setInitialSize(initialSize);
            db.setMaxActive(maxActive);
            dataSource = db;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
        }
       
    }
    
    public Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
        }
    }
    
}
