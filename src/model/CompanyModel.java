package model;

/*
 *******************************************************************************************************
 *   @Class Name         :   CompanyModel
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   This model class represents a company entity with fields such as
 *                           name, username, password, email, and id.
 *******************************************************************************************************
 */
public class CompanyModel {
    private int id;
    private String name, username, password, email;

    /*
     ***********************************************************************************************
     *  @Constructor   : CompanyModel(String name, String username, String password, String email)
     *  @Description   : Initializes a company instance with provided name, username, password and email.
     ***********************************************************************************************
     */
    public CompanyModel(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
