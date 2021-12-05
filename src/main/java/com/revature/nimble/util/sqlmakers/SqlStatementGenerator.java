package com.revature.nimble.util.sqlmakers;

/*
    Abstract class for all SQL statement generators to extend
*/
abstract class SqlStatementGenerator<T> {
    public String sql_statement ="";//every sql-statement-generator will return a String like statement at the end
    public T savingObject;          //used when having object and want to manipulate data in database
    public Class<T> tableType;          //used when providing info and desiring an object
    public String tableName;
    public String key;
    public T keyValue;
    public String keyValueString;

    public SqlStatementGenerator(){}

    public String toSQL() throws IllegalAccessException, InstantiationException {return null;}

    void setKeyValueString(){keyValueString=valueToStringHelper(keyValue);}

    //different datatype needs different sql_statement syntax
    protected <V> String valueToStringHelper(V value){
        //in case of null: " is null "
        if(value==null) return" is null ";
        //in case of String "= 'keyValue' "
        else if(value.getClass()==String.class) return" = '"+keyValue.toString()+"' ";
        //in case of other object just to string "= keyValue"
        else return " = "+keyValue.toString()+" ";
    }
}
