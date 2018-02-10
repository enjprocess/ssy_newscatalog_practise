package com.shengsiyuan.imis.util;

import java.sql.Connection;
/**
 *
 * <p>Title: TransactionManager</p>
 * <p>Description: 无状态的对象,用于生成TransactionContext对象,及调用TC对象的方法 </p>
 * <p>Company: 自由职业</p> 
 * @author lsw
 * @date 2018年2月10日
 */
public class TransactionManager {

    /**
     * 将TransactionContext对象生成的过程封装起来，从而可以在一个service对象中的不同方法中各自生成TC对象
     */
    public TransactionContext beginTransaction() {
        return new TransactionContext(DBConnectionFactory.getInstance.getConnection());
    }
    
    public void commitTransaction(TransactionContext context) {
        context.commitTransaction();
    }
    
    public void rollbackTransaction(TransactionContext context) {
        context.rollbackTransaction();
    }

}
