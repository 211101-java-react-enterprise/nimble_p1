package com.revature.nimble;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;
import com.revature.nimble.exceptions.MissingAnnotationException;
import com.revature.nimble.util.converters.ListObjectConverter;
import com.revature.nimble.util.converters.ToObjectConverter;
import com.revature.nimble.util.sqlmakers.*;
import com.revature.nimble.util.connections.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class OrmServiceDriver {
    String statement;

    public OrmServiceDriver(){}


    //Helper function: check if annotation present
    private <T> boolean annotationChecker(Class<T> targetType){
        if (!targetType.isAnnotationPresent(Table.class)){//if given class missing table mapping, throws exception
            throw new MissingAnnotationException("No Annotation found on"+targetType.toString());
        }
        else {//if any fields under such class missing column mapping, throws exception
            Arrays.stream(targetType.getDeclaredFields()).forEach(field -> {
                if (!(field.isAnnotationPresent(Column.class)||field.isAnnotationPresent(Key.class))){
                    throw new MissingAnnotationException("No Annotation found on "+field.toString());
                }
            });
        }
        return true;
    }

    public <T> T creating(T object) throws IllegalAccessException {
        if (annotationChecker(object.getClass())) {
            statement = new Insert(object).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted != 0) {
                    return object;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T, S> T reading(Class tableType, S keyValue) throws IllegalAccessException, InstantiationException {
        if (annotationChecker(tableType)) {
            statement = new Select(tableType, keyValue).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()) {
                    return (T) new ToObjectConverter(tableType, resultSet).toObject();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T, S> List<T> reading(Class tableName, Field columnName, S searchValue) throws IllegalAccessException, InstantiationException {
        if (annotationChecker(tableName)) {
            statement = new SelectAll(tableName, columnName, searchValue).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet != null) {
                    return (List<T>) new ListObjectConverter<>(tableName, resultSet).obtainList();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




    public <T> boolean delete(Class<T> tableName, T fieldName) {
        if (annotationChecker(tableName)) {
            statement = new Delete(tableName, fieldName).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                int result = pstmt.executeUpdate();
                if (result != 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public <T> boolean update(Class<T> tableName, T keyValue, Field f, T fieldValue) {
        if (annotationChecker(tableName)) {
            statement = new Update(tableName, keyValue, f, fieldValue).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                int result = pstmt.executeUpdate();
                if (result != 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
