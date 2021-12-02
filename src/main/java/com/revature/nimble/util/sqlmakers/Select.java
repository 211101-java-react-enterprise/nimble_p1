package com.revature.nimble.util.sqlmakers;



import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.util.Arrays;

public class Select<T> extends SqlStatementGenerator{

    public Select(Class tableType,T keyValue ){
        super.tableType =tableType;
        Arrays.stream(tableType.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Key.class))key= field.getName();
        });
        this.keyValue =keyValue;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        if(tableType.isAnnotationPresent(Table.class)) {
            tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
            sql_statement = "Select * from " + tableName + " where " + key + " = " + "'" + keyValue.toString() + "';";
            return sql_statement;
        }
        else{
            return null;
        }
    }
}
