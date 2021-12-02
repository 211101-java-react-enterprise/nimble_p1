package com.revature.nimble;

import com.revature.nimble.TEMP.TempDao;
import com.revature.nimble.TEMP.TempServcie;
import com.revature.nimble.TEMP.TempUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TempDriver {

    //TODO: Temp Driver for testing purpose
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, NoSuchFieldException {

        //prepare a
        BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome Please Input you info");
        System.out.println("id");
        int id=Integer.parseInt(consolReader.readLine());
        System.out.println("username");
        String username=consolReader.readLine();
        TempUsers test=new TempUsers(username,id);
        TempDao dao=new TempDao();
        TempServcie servcie=new TempServcie(dao);
        System.out.println(servcie.register(test));
        //System.out.println(servcie.delete(TempUsers.class,4));
//        System.out.println(servcie.isUser("3").getUsername());
//        if(servcie.update(TempUsers.class,3,TempUsers.class.getField("username"),"HelloWorld!")){
//            System.out.println(servcie.isUser("3").getUsername());
//        }
//        TempUsers users= servcie.isUser("123");
//        System.out.println("Username: "+users.getUsername()+" Id: "+users.getId());
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
