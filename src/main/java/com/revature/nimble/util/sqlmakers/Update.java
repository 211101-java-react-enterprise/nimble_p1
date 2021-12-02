package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;

public class Update<T> extends SqlStatementGenerator{
    String fieldName;
    T fieldValue;
    public Update(Class table, T keyValue, Field field, T fieldValue){
        super.tableType =table;
        super.keyValue=keyValue;
        this.fieldName=((Column)field.getAnnotation(Column.class)).columnName();
        this.fieldValue=fieldValue;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        if(tableType.isAnnotationPresent(Table.class)) {
            tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
            Field[] fields = tableType.getDeclaredFields();
            for (Field field : fields) {//traversal attributes
                if (field.isAnnotationPresent(Key.class))key=field.getAnnotation(Key.class).keyName();
            }
            sql_statement = "Update " + tableName + " set " + fieldName+"= '"+ fieldValue+
                            "' where " +key + " = " + "'" + keyValue.toString() + "';";
            return sql_statement;
        }
        else{
            return null;
        }
    }
}
