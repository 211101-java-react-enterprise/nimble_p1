package com.revature.nimble.util.sqlmakers;

/*
    Abstract class for all SQL statement generators to extend
*/
abstract class SqlStatementGenerator<T> {
    public String sql_statement ="";//every sql-statement-generator will return a String like statement at the end
    public T targetObject;          //used when having object and want to manipulate data in database
    public Class<T> table;          //used when providing info and desiring an object
    public String tableName;
    public String key;
    public T keyValue;

    public SqlStatementGenerator(){}

    public String toSQL() throws IllegalAccessException, InstantiationException {return null;}
}
