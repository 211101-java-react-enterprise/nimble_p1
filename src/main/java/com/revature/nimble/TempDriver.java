package com.revature.nimble;

import com.revature.nimble.TEMP.TempDao;
import com.revature.nimble.TEMP.TempServcie;
import com.revature.nimble.TEMP.TempUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class TempDriver {

    //TODO: Temp Driver for testing purpose
    public static <T> void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, NoSuchFieldException {

        //prepare a
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome Please Input you info");
        System.out.println("id");
        int id=Integer.parseInt(consoleReader.readLine());
        System.out.println("username");
        String username=consoleReader.readLine();
        TempUsers test=new TempUsers(username,id);
        TempDao dao=new TempDao();
        TempServcie servcie=new TempServcie(dao);
//        List<TempUsers> usersList=servcie.selectall(TempUsers.class.getField("note"),null);
//        for(TempUsers t: usersList){
//            System.out.println(t.getUsername()+"  :  "+t.getId());
//        }
        //System.out.println(servcie.delete(TempUsers.class,4));
//        if(servcie.isUser(null)==null) System.out.println("Empty");
        if(servcie.update(TempUsers.class,4,TempUsers.class.getField("username"),"HappyQi")){
            System.out.println(servcie.isUser("4").getUsername());
        }
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
