package com.revature.nimble.TEMP;

import com.revature.nimble.OrmService;

import java.lang.reflect.Field;
import java.util.List;

public class TempDao{

    /*
    Create: Register
    Update: user changing info/ ticket change info
    Read  : login
    Delete: Delete, delete ticket: problem session

    Servlet
     */

//    public <T> T insert(T newInstance, Class<T> type){
//        return null;
//    }
    public Object insert(Object newObj) throws IllegalAccessException, InstantiationException {
        //TODO: ORM Service class
        OrmService s=new OrmService(newObj);
        System.out.println("inserting");
        return s.saving();
    }
    public <T> T findById(String keyValue) throws IllegalAccessException, InstantiationException {
        OrmService s=new OrmService();
        return (T) s.reading(TempUsers.class, keyValue);
    }
    public <T> boolean removeById(Class<T> type, T keyValue) throws IllegalAccessException, InstantiationException {
        OrmService s=new OrmService();
        return s.delete(type,keyValue);
    }
    public <T> boolean update(Class<T> type, T keyValue, Field f, T fieldValue) throws IllegalAccessException, InstantiationException {
        OrmService s=new OrmService();
        return s.update(type,keyValue,f,fieldValue);
    }


}
