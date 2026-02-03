package org.Grupp4;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "api_user")
public class ApiUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private UUID apiKey;
private String userName;
private String userPassword;



public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public void setApiKey(UUID apiKey) {
    this.apiKey = apiKey;
}

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
