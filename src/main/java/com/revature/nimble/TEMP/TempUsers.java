package com.revature.nimble.TEMP;


import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

@Table(tableName = "app_user")
public class TempUsers {
    @Column(columnName = "username")
    public String username;
    @Key(keyName = "id")
    public String id;

    public TempUsers(String username, String id){
        this.username=username;
        this.id=id;
    }
    public TempUsers(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
