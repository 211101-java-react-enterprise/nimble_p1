package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Update<T> extends SqlStatementGenerator{
    String updatingField;
    T updateValue;
    public Update(Class table, T keyValue, Field updatingField, T updateValue){
        super.tableType =table;
        super.keyValue=keyValue;
        this.updatingField =updatingField.getAnnotation(Column.class).columnName();
        this.updateValue =updateValue;
    }

    @Override
    public String toSQL(){
        //get table name mapped to given class
        tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
        //Going through fields of the class and find key name
        Arrays.stream(tableType.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Key.class)) key = field.getAnnotation(Key.class).keyName();
        });
        super.setKeyValueString();
        sql_statement = "Update " + tableName + " set " + updatingField + "= '" + updateValue +
                "' where " + key +keyValueString+ ";";
        return sql_statement;
    }
}
