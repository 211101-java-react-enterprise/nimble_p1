package com.revature.nimble.TEMP;


import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

@Table(tableName = "app_user")
public class TempUsers {
    @Column(columnName = "username")
    public String username;
    @Key(keyName = "id")
    public int id;
    @Column(columnName = "note")
    public String note;

    public TempUsers(String username, int id){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
