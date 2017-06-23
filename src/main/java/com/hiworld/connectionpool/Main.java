package com.hiworld.connectionpool;

import java.beans.PropertyVetoException;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Main {

    public static void main(String[] args) throws PropertyVetoException {
        
        MutiConnectionPool mutiConnectionPool = new MutiConnectionPool();
        
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://xxx:3306/xxx");
        druidDataSource.setUsername("xxx");
        druidDataSource.setPassword("xxx");
        
        String druidDsName = "druidpool";
        mutiConnectionPool.addDatasource(druidDsName, druidDataSource);
        
        String sql = "update task_offline a set a.group_desc='2' where a.id='5842'";
        mutiConnectionPool.execute(druidDsName, sql);
        
        ComboPooledDataSource cp30Datasource = new ComboPooledDataSource();
        cp30Datasource.setDriverClass("com.mysql.jdbc.Driver");
        cp30Datasource.setJdbcUrl("jdbc:mysql://xxx:3306/xxx");
        cp30Datasource.setUser("xxx");
        cp30Datasource.setPassword("xxx");
        String cp30DsName = "cp30pool";
        mutiConnectionPool.addDatasource(cp30DsName, cp30Datasource);
        sql = "update task_offline a set a.group_desc='3' where a.id='5842'";
        mutiConnectionPool.execute(cp30DsName, sql);
    }

}
