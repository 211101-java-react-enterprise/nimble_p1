package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;

public class Delete extends SqlStatementGenerator {
    public <T> Delete(Class table, T keyValue){
        super.table=table;
        super.keyValue=keyValue;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        if(table.isAnnotationPresent(Table.class)) {
            tableName = ((Table) table.getAnnotation(Table.class)).tableName();
            Field[] fields = table.getDeclaredFields();
            for (Field field : fields) {//traversal attributes
                if (field.isAnnotationPresent(Key.class))key=field.getAnnotation(Key.class).keyName();
            }
            sql_statement = "Delete from " + tableName + " where " + key + " = " + "'" + keyValue.toString() + "';";
            return sql_statement;
        }
        else{
            return null;
        }
    }
}
