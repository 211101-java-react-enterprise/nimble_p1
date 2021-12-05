package com.revature.nimble.util.converters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListObjectConverter<T> {
    private List<T> objectList=new ArrayList<>();
    private ResultSet resultSet;
    private Class<T> tableType;

    public ListObjectConverter(Class tableType, ResultSet resultSet){
        this.resultSet=resultSet;
        this.tableType=tableType;
    }

    public List<T>  obtainList() throws SQLException, InstantiationException, IllegalAccessException {
        try {
            while (resultSet.next()) {
                objectList.add((T) new ToObjectConverter<T>(tableType,resultSet).toObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectList;
    }
}
