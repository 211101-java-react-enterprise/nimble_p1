package com.revature.nimble.util.sqlmakers;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;
import com.revature.nimble.exceptions.WrongAnnotationException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

public class Insert extends SqlStatementGenerator{
    private HashMap entries=new HashMap();

    public Insert(Object object) {
        savingObject =object;
    }

    @Override
    public String toSQL() throws IllegalAccessException{
        tableName = savingObject.getClass().getAnnotation(Table.class).tableName();
        //get all fields of an object, generate a Map to store field_name:column_name pair
        Arrays.stream(savingObject.getClass().getDeclaredFields()).forEach(field -> {
            try {
                if (field.isAnnotationPresent(Key.class)) {
                    String fieldName = (field.getAnnotation(Key.class).keyName());
                    Object fieldValue = field.get(savingObject);
                    entries.put(fieldName, fieldValue);
                } else if (field.isAnnotationPresent(Column.class)) {
                    String fieldName = (field.getAnnotation(Column.class).columnName());
                    Object fieldValue = field.get(savingObject);
                    entries.put(fieldName, fieldValue);
                }else {//We checked annotation existing in driver, if not column nor key, must be wrong annotation applied
                    throw new WrongAnnotationException("Wrong Annotation Applied to field"+field.getName());
                }
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        });

        //Don't know how to generate string right out of hashmap in such pattern.... use stringBuffer as alternative
        StringBuffer valueList=new StringBuffer();
        StringBuffer fieldNameList=new StringBuffer();
        entries.keySet().stream().forEach(entry -> {
            fieldNameList.append(entry.toString()+",");
            valueList.append(toStringHelper(entries.get(entry)) +" ,");
        });
        String columns=fieldNameList.substring(0,fieldNameList.length()-1);
        String values=valueList.substring(0, valueList.length()-1);

        sql_statement = "insert into "+ tableName +" ("+columns+") "+"values ("+values+");";
        return sql_statement;
    }


    private <T> String toStringHelper(T value){
        if(value==null)return " null ";
        else if(value.getClass()==String.class)return " '"+value+"' ";
        else return " "+value.toString()+" ";
    }
}
