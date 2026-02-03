package org.Grupp4;

import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class User {
    
private UUID apiKey;
private String userName;
private String userPassword;


public UUID getApiKey() {
    return apiKey;
}

public String getUserName() {
    return userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}

public String getUserPassword() {
    return userPassword;
}

public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
}
    

    
}
