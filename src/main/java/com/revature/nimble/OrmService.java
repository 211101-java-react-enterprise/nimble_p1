package com.revature.nimble;

import com.revature.nimble.util.converters.ToObjectConverter;
import com.revature.nimble.util.sqlmakers.Delete;
import com.revature.nimble.util.sqlmakers.Insert;
import com.revature.nimble.util.sqlmakers.Select;
import com.revature.nimble.util.sqlmakers.Update;
import com.revature.nimble.util.connections.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrmService {
    Object object;
    String statement;

    public OrmService(Object object){
        this.object=object;
    }
    public OrmService(){}

    public Object saving() throws IllegalAccessException, InstantiationException {
        statement= new Insert(this.object).toSQL();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //prepare SQl statements
            PreparedStatement pstmt = conn.prepareStatement(statement);
            //Execute SQL query and return new user
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 0) {return object;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T reading(Class tableName, T keyValue) throws IllegalAccessException, InstantiationException {
        statement=new Select(tableName,keyValue).toSQL();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //prepare SQl statements
            PreparedStatement pstmt = conn.prepareStatement(statement);
            //Execute SQL query and return new user
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet!=null){
                return (T) new ToObjectConverter(tableName,resultSet).toObject();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T> boolean delete(Class<T> tableName, T fieldName) throws IllegalAccessException, InstantiationException {
        statement=new Delete(tableName,fieldName).toSQL();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //prepare SQl statements
            System.out.println(statement);
            PreparedStatement pstmt = conn.prepareStatement(statement);
            //Execute SQL query and return new user
            int result = pstmt.executeUpdate();
            if(result!=0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public <T> boolean update(Class<T> tableName, T keyValue, Field f, T fieldValue) throws IllegalAccessException, InstantiationException {
        statement=new Update(tableName,keyValue,f,fieldValue).toSQL();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //prepare SQl statements
            System.out.println(statement);
            PreparedStatement pstmt = conn.prepareStatement(statement);
            //Execute SQL query and return new user
            int result = pstmt.executeUpdate();
            if(result!=0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
