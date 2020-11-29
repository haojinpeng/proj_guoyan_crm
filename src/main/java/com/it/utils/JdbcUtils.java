package com.it.utils;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author     ：zyx
 * @date       ：Created in 2020/8/17 17:10
 * @description：
 * @modified By：
 * @version:     $
 */
 
public class JdbcUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String pwd;


    //创建连接池

    public static BasicDataSource basicDataSource=new BasicDataSource();

    //创建静态块

    static{

        InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            Properties pp=new Properties();
            pp.load(in);
//            pp.load(new FileReader(System.getProperty("user.dir")+"\\src\\db.properties"));

            driver=pp.getProperty("driver");
            url=pp.getProperty("url");
            user=pp.getProperty("user");
            pwd=pp.getProperty("pwd");


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(driver);
        System.out.println(url);

        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pwd);
    }


    public static DataSource getDataSource(){
        return basicDataSource;
    }

    public static void main(String[] args) {
         new JdbcUtils().toString();
    }

}
