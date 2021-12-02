package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

import java.lang.reflect.Field;
import java.util.HashMap;

public class Insert extends SqlStatementGenerator{
    private HashMap entries=new HashMap();


    public Insert(Object object) {
        targetObject=object;
    }

    @Override
    public String toSQL() throws IllegalAccessException, InstantiationException {
        //Prepare Information needed to generate sql statement
        Class tableClass=targetObject.getClass();
        if(tableClass.isAnnotationPresent(Table.class)){//Check annotation exist
            //get table Name
            tableName =((Table)targetObject.getClass().getAnnotation(Table.class)).tableName();
            //get fields of an object, generate a Map to store field:column_name pair
            Field[] fields = targetObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Key.class)) {
                    String fieldname=(field.getAnnotation(Key.class).keyName()).toString();
                    Object fieldValue=field.get(targetObject);
                    entries.put(fieldname, fieldValue);
                }
                else if(field.isAnnotationPresent(Column.class)){
                    String fieldname=(field.getAnnotation(Column.class).columnName()).toString();
                    Object fieldValue=field.get(targetObject);
                    entries.put(fieldname, fieldValue);
                }
                else{
                    System.out.println("Invalid fields: Missing Annotation");
                }
            }
        }
        else{
            System.out.println("Invalid Object provided: Missing Annotation");
        }

        sql_statement ="insert into "+ tableName +"";
        StringBuffer value=new StringBuffer();
        StringBuffer tablename=new StringBuffer();
        entries.keySet().stream().forEach(entry -> {
            tablename.append(entry.toString()+",");
            value.append(" '"+entries.get(entry) +"' ,");
        });
        String table_t=tablename.substring(0,tablename.length()-1);
        String value_v=value.substring(0, value.length()-1);

        sql_statement = sql_statement.concat(" ("+table_t+") "+"values ("+value_v+");");
        return sql_statement;
    }
}
