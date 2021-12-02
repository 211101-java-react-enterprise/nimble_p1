package com.revature.nimble.TEMP;

import java.lang.reflect.Field;

public class TempServcie {
    TempDao dao;
    public TempServcie(TempDao dao){
        this.dao=dao;
    }

    public boolean register(TempUsers users) throws IllegalAccessException, InstantiationException {
        System.out.println("registering");
        if(dao.insert(users)!=null) return true;
        return false;
    }
    public TempUsers isUser (String keyValue) throws IllegalAccessException, InstantiationException {
        return dao.findById(keyValue);
    }
    public <T> boolean delete (Class objectClass, T keyValue) throws IllegalAccessException, InstantiationException {
        return dao.removeById(objectClass,keyValue);
    }
    public <T> boolean update (Class objectClass, T keyValue, Field f, T fieldValue) throws IllegalAccessException, InstantiationException {
        return dao.update(objectClass,keyValue,f,fieldValue);
    }
}
