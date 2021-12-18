package com.saasym.essentials;


import com.saasym.essentials.config.DataSourceConfig;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public final class Essentials extends JavaPlugin {
    private static Essentials instance;
    private static DataSource mainDataSource;
    private static Connection dsConnection;

    @Override
    public void onLoad(){
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        instance = this;

        mainDataSource = DataSourceConfig.initDataSource(
                getConfig().getConfigurationSection("datasource").getString("serverIP"),
                getConfig().getConfigurationSection("datasource").getString("port"),
                getConfig().getConfigurationSection("datasource").getString("database"),
                getConfig().getConfigurationSection("datasource").getString("username"),
                getConfig().getConfigurationSection("datasource").getString("password")
                );

        try{
            dsConnection = mainDataSource.getConnection();
        }catch (SQLException e){
            getLogger().log(Level.SEVERE,e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        try{
            dsConnection.close();
        }catch (SQLException e){
            getLogger().log(Level.SEVERE,e.getMessage());
        }
    }

    public static Essentials getInstance(){
        return instance;
    }
    public static DataSource getMainDataSource(){
        return mainDataSource;
    }
    public static Connection getDsConnection(){
        return dsConnection;
    }
}
