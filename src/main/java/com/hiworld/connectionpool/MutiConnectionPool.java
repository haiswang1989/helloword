package com.hiworld.connectionpool;

import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.base.Preconditions;

/**
 * 多数据源连接池
 * <p>Description:</p>
 * @author hansen.wang
 * @date 2017年6月23日 上午9:52:25
 */
public class MutiConnectionPool {
    
    ConcurrentHashMap<String, JdbcTemplate> mutiDatasourcePool = new ConcurrentHashMap<>();
    
    public void removeDatasource(String datasourceName) {
        checkDatasourceName(datasourceName);
        mutiDatasourcePool.remove(datasourceName);
    }
    
    public void addDatasource(String datasourceName, DataSource datasource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
        mutiDatasourcePool.put(datasourceName, jdbcTemplate);
    }
    
    /**
     * 
     * @param datasourceName
     * @param sql
     */
    public void execute(String datasourceName, String sql) {
        JdbcTemplate jdbcTemplate = checkDatasourceName(datasourceName);
        jdbcTemplate.execute(sql);
    }
    
    /**
     * 
     * @param datasourceName
     * @return
     */
    public JdbcTemplate checkDatasourceName(String datasourceName) {
        JdbcTemplate targetJdbcTemplate = mutiDatasourcePool.get(datasourceName);
        Preconditions.checkNotNull(targetJdbcTemplate, String.format("Unknow datasource name %s", datasourceName));
        return targetJdbcTemplate;
    }
    
    public void shutdown() {
        mutiDatasourcePool.clear();
    }
    
}
