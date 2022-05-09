package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerDAO{
    public void header(String s) {
        System.out.printf("%n==========%s=========%n", s);
    }
    public void displayAllCustomers() {
        List<Customers> allCustomers = this.getAllCustomers();
        this.header("List All Customers");
        for (Customers c : allCustomers) {
            System.out.printf("ID:%d Name:%s %s%n", c.getId(), c.getFname(), c.getLname());
        }
        System.out.println();
    }
    @Override
    public List<Customers> getAllCustomers() {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        List<Customers> cList = new ArrayList<>();
        try {
            db.ps = db.conn.prepareStatement(SQL.getAllCustomers.getQuery());
            db.rs = db.ps.executeQuery();
            while(db.rs.next()){
                Customers c = new Customers(db.rs.getInt(1), db.rs.getString(2),db.rs.getString(3),db.rs.getString(4));
                cList.add(c);
            }
            return cList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            db.dispose();
        }
    }

    @Override
    public Customers getCustomerById(int id) {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        Customers c = new Customers();
        try {
            db.ps= db.conn.prepareStatement(SQL.getCustomerById.getQuery());
            db.ps.setInt(1,id);
            db.rs = db.ps.executeQuery();
            if (db.rs.next()) {
                c.setId(db.rs.getInt(1));
                c.setFname(db.rs.getString(2));
                c.setLname(db.rs.getString(3));
                c.setEmail(db.rs.getString(4));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        } finally {
            db.dispose();
        }
        return c;
    }

    @Override
    public boolean addCustomer(Customers c) {
        this.header("Adding A Customer");
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        try {
            db.ps = db.conn.prepareStatement(SQL.addCustomer.getQuery());
            db.ps.setString(1, c.getFname());
            db.ps.setString(2, c.getLname());
            db.ps.setString(3, c.getEmail());
            db.rs = db.ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.dispose();
        }
    }

    @Override
    public boolean removeCustomerById(int id) {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        try {
            db.ps = db.conn.prepareStatement(SQL.removeCustomerById.getQuery());
            db.ps.setInt(1,id);
            db.rs = db.ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.dispose();
        }
    }
}
