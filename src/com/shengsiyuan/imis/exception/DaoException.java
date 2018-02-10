package com.shengsiyuan.imis.exception;

import com.shengsiyuan.imis.util.MessageHelper;

/**
 * 
 * <p>Title: DaoException</p>
 * <p>Description: Dao层抛出的异常对象</p>
 * <p>Company: 自由职业</p> 
 * @author lsw
 * @date 2018年2月10日
 */
public class DaoException extends Exception {
    
    private int errorCode;

    public DaoException(Throwable e) {
        super(e);
    }
    
    public DaoException(int errorCode) {
        super(MessageHelper.getExceptionInfo(errorCode));
        this.errorCode = errorCode;
    }
    
    public DaoException(int errorCode, Throwable cause) {
        super(MessageHelper.getExceptionInfo(errorCode), cause);
        this.errorCode = errorCode;
    }
    
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getExceptionMessage() {
        return getMessage();
    }
    
    public int getErrorCode() {
        return errorCode;
    }
}
