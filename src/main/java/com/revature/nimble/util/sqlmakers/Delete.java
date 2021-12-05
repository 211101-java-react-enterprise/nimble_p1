package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.util.Arrays;

public class Delete extends SqlStatementGenerator {
    public <T> Delete(Class table, T keyValue){
        super.tableType =table;
        super.keyValue=keyValue;
    }

    @Override
    public String toSQL() {
        //Get table name mapped to the type
        tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
        //Going through the fields of such class, find key
        Arrays.stream(tableType.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Key.class)) key = field.getAnnotation(Key.class).keyName();
        });
        //call key value setter to properly handle different datatype
        super.setKeyValueString();
        sql_statement = "Delete from " + tableName + " where " + key +keyValueString+ ";";
        return sql_statement;
    }
}
