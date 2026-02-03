package org.Grupp4.ApiUser;

public class ApiDTO {

    private String newUserName;
    private String newPassword;

    public String getnewUserName() {
        return newUserName;
    }

    public void setnewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getnewPassword() {
        return newPassword;
    }

    public void setnewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}