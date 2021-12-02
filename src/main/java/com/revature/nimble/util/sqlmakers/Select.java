package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.util.Arrays;

public class Select<T> extends SqlStatementGenerator{

    public Select(Class tableType,T keyValue ){
        super.tableType =tableType;
        this.keyValue =keyValue;
    }

    @Override
    public String toSQL() {
        //Get table name mapped to this class
        tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
        //going through the fields and find key name
        Arrays.stream(tableType.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Key.class)) key = field.getName();
        });
        sql_statement = "Select * from " + tableName + " where " + key + " = " + "'" + keyValue.toString() + "';";
        return sql_statement;
    }
}
