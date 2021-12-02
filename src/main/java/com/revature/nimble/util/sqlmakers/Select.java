package com.revature.nimble.util.sqlmakers;



import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Select<T> extends SqlStatementGenerator{

    public Select(Class table,T selectingColumn ){
        super.table=table;
        Arrays.stream(table.getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Key.class))key= field.getName();
        });
        keyValue=selectingColumn;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        if(table.isAnnotationPresent(Table.class)) {
            tableName = ((Table) table.getAnnotation(Table.class)).tableName();
            sql_statement = "Select * from " + tableName + " where " + key + " = " + "'" + keyValue.toString() + "';";
            return sql_statement;
        }
        else{
            return null;
        }
    }
}
