package com.saasym.essentials.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    public static DataSource initDataSource(String serverIP,String port,String database,String username,String password){
        String jdbcUrl="jdbc:mysql://"+serverIP+":"+port+"/"+database+"?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }
}
