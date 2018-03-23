package com.shengsiyuan.imis.exception;

public class ErrorCode {

    //数据源层面的异常
    /////////////////////////////////////////////////////////////////////////
    //数据库连接错误
    public final static int DB_CONNECTION_ERROR = 0000;
    //数据库提交错误
    public final static int DB_COMMIT_ERROR = 0001;
    //数据库回滚错误
    public final static int DB_ROLLBACK_ERROR = 0002;
    
    //Dao层面的异常
    /////////////////////////////////////////////////////////////////////////
    
    public final static int SQL_ERROR = 1000;
    
    //Service层面的异常
    /////////////////////////////////////////////////////////////////////////
    //显示新闻类别错误
    public final static int LIST_NEWSCATALOG_ERROR= 2000;
    //增加新闻类别错误
    public final static int ADD_NEWSCATALOG_ERROR= 2001;
    //更新新闻类别错误
    public final static int UPDATE_NEWSCATALOG_ERROR= 2002;
    //删除新闻列表错误
    public final static int DELETE_NEWSCATALOG_ERROR = 2003;
    
    //其它异常
    /////////////////////////////////////////////////////////////////////////
    //新闻类别已经存在
    public final static int REPEAT_NEWSCATALOG_ERROR= 9900;
    
    
}
