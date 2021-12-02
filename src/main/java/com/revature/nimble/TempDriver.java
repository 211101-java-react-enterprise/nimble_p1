package com.revature.nimble;

import com.revature.nimble.TEMP.TempDao;
import com.revature.nimble.TEMP.TempServcie;
import com.revature.nimble.TEMP.TempUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TempDriver {

    //TODO: Temp Driver to testing purpose
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, NoSuchFieldException {

        //prepare a
        BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome Please Input you info");

//        String statement = new Select(test).toSQL();
//        System.out.println(statement);
//        String insert=new Insert(test).toSQL();
//        System.out.println(insert);
        TempDao dao=new TempDao();
        TempServcie servcie=new TempServcie(dao);
        TempUsers users= servcie.isUser("123");
        System.out.println("Username: "+users.getUsername()+" Id: "+users.getId());
        //System.out.println(servcie.register(test));
        //System.out.println(servcie.update(TempUsers.class, "2" ,TempUsers.class.getDeclaredField("username"), "HelloWorld"));

//        Class userClass = TempUsers.class;//Get User class information
//        if (userClass.isAnnotationPresent(Table.class)) {//Determine whether userClass uses the Table annotation
//            Table table = (Table) userClass.getAnnotation(Table.class);//Getting annotation information
//            System.out.println(table.tableName());//tableName parameter that outputs Table annotations
//        }
//        Field[] fields = userClass.getDeclaredFields();
//        for (Field field : fields) {//traversal attributes
//            if (field.isAnnotationPresent(Column.class)) {//With Column annotations
//                Column column = (Column) field.getAnnotation(Column.class);//Getting annotation information
//                System.out.println(column.columnName());
//            }
//        }
    }
}
