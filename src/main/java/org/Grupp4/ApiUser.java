package org.Grupp4;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "api_user")
public class ApiUser {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private UUID apiKey;
@Size(min= 4, max = 99, message = "Username is too short or too long")
private String userName;
@Size(min= 4, max = 99, message = "Password is too short or too long")
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
