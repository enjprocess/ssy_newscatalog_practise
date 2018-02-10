package com.shengsiyuan.imis.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.shengsiyuan.imis.exception.ConnectionException;
import com.shengsiyuan.imis.exception.ErrorCode;
/**
 * 
 * <p>Title: TransactionContext</p>
 * <p>Description: 将连接对象封装到TransactionContext对象中,并一同将数据库对象处理过程中出现的异常解决掉</p>
 * <p>Company: 自由职业</p> 
 * @author lsw
 * @date 2018年2月10日
 */
public class TransactionContext {

    private Connection conn;
    
    public TransactionContext(Connection conn) {
        this.conn = conn;
        
        if (null == conn) {
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
        }
        
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
        } 
    }
    
    public void rollbackTransaction() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_ROLLBACK_ERROR));
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
            }
        }
    }
    
    public void commitTransaction() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_COMMIT_ERROR));
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ConnectionException(MessageHelper.getExceptionInfo(ErrorCode.DB_CONNECTION_ERROR));
                }
            }
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
}
