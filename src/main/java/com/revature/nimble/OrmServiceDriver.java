package com.revature.nimble;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;
import com.revature.nimble.exceptions.MissingAnnotationException;
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
import java.util.Arrays;

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

    public Object creating(Object object) throws IllegalAccessException, InstantiationException {
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

    public <T> T reading(Class tableName, T keyValue) throws IllegalAccessException, InstantiationException {
        if (annotationChecker(tableName)) {
            statement = new Select(tableName, keyValue).toSQL();
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                //prepare SQl statements
                PreparedStatement pstmt = conn.prepareStatement(statement);
                //Execute SQL query and return new user
                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet != null) {
                    return (T) new ToObjectConverter(tableName, resultSet).toObject();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public <T> boolean delete(Class<T> tableName, T fieldName) throws IllegalAccessException, InstantiationException {
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
    public <T> boolean update(Class<T> tableName, T keyValue, Field f, T fieldValue) throws IllegalAccessException, InstantiationException {
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
