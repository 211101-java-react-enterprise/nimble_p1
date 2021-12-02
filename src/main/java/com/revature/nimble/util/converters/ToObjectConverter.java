package com.revature.nimble.util.converters;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToObjectConverter<T> {
    private T object;
    private ResultSet resultSet;
    private Class<T> table;

    public ToObjectConverter(Class table, ResultSet resultSet){
        this.table=table;
        this.resultSet=resultSet;
    }

    public T toObject() throws InstantiationException, IllegalAccessException, SQLException {
        if (table.isAnnotationPresent(Table.class)) {
            if (resultSet.next()) {
                object= table.newInstance();
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {//traversal attributes
                    if (field.isAnnotationPresent(Column.class)) {//With Column annotations
                        String column = field.getAnnotation(Column.class).columnName();//Getting annotation information
                        field.set(object, resultSet.getObject(column));
                    } else if (field.isAnnotationPresent(Key.class)) {
                        String key = field.getAnnotation(Key.class).keyName();
                        field.set(object, resultSet.getObject(key));
                    }

                }
            }
            return object;
        }
        else{
            System.out.println("Missing Annotation");
        }
        return null;
    }

}
