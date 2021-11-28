package com.revature.nimble.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    Move ConnectionFactory to ORM project, so the customer project don't need to bother the connection part anymore
*/
public class ConnectionFactory {

    //Create a singleton connection
    private static final ConnectionFactory connectionFactory= new ConnectionFactory();
    //Load database uri and credentials from property file
    private final Properties props=new Properties();

    //get method returns the singleton Connection Factory to customer function
    public static ConnectionFactory getInstance(){return connectionFactory;}

    //load properties file for further action
    private ConnectionFactory() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load uri and credentials for database connection
    public Connection getConnection(){
        Connection conn = null;
        try{
            conn= DriverManager.getConnection(props.getProperty("url"),
                                              props.getProperty("username"),
                                              props.getProperty("password"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }


}
