package org.example;

public class Customers {
    private int id;
    private String email;
    private String lname;
    private String fname;

    public Customers(){}

    public Customers(String fname, String lname, String email) {
        this.email = email;
        this.lname = lname;
        this.fname = fname;
    }
    public Customers(int id, String fname, String lname, String email) {
        this.id=id;
        this.email = email;
        this.lname = lname;
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
