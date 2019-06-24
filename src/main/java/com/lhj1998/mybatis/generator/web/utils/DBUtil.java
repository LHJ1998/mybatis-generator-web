package com.lhj1998.mybatis.generator.web.utils;

import com.lhj1998.mybatis.generator.web.dto.DBLinkInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//jdbc:mysql://localhost:3306/music
@Slf4j
public class DBUtil {

    public static Connection getConnection(DBLinkInfo info){
        Connection conn = null;
        try {
            Class.forName(info.getDriver());
            conn = DriverManager.getConnection(info.getUrl(), info.getUsername(), info.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("找不到驱动类");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取数据库连接失败");
            return null;
        }
        return conn;
    }

}
