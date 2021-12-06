package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SelectAll<S> extends SqlStatementGenerator{

    private String columnName;
    private S searchValue;

    public SelectAll(Class tableName) {
        tableType=tableName;
    }

    public SelectAll(Class tableName, Field columnName, S searchValue) {
        tableType=tableName;
        this.columnName=columnName.getName();
        this.searchValue=searchValue;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        //Get table name mapped to this class
        tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
        //going through the fields and find key name
        String searchingValue=super.valueToStringHelper(searchValue);
        sql_statement = "Select * from " + tableName + " where " + columnName +searchingValue+";";
        return sql_statement;
    }

    public String star() {
        tableName = ((Table) tableType.getAnnotation(Table.class)).tableName();
        sql_statement = "Select * from " + tableName + " ;";

        return sql_statement;
    }
}
