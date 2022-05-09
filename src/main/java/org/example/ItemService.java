package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements ItemDAO{
    public void header(String s) {System.out.printf("%n==========%s==========%n", s);}

    public void displayAllItems() {
        List<Items> allItems = this.getAllItems();
        this.header("List of Items");
        for (Items i : allItems) {
            System.out.printf("ID:%d Name:%s%n", i.getId(), i.getName());
        }
        System.out.println();
    }

    @Override
    public List<Items> getAllItems() {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        List<Items> itemsList = new ArrayList<>();
        try {
            db.ps = db.conn.prepareStatement(SQL.getAllItems.getQuery());
            db.rs = db.ps.executeQuery();

            while(db.rs.next()){
                Items i = new Items(db.rs.getInt(1),db.rs.getString(2),db.rs.getDouble(3));
                itemsList.add(i);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.dispose();
        return itemsList;
    }
    @Override
    public boolean addItem(Items i) {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        try {
            db.ps = db.conn.prepareStatement(SQL.addItem.getQuery());
            db.ps.setString(1, i.getName());
            db.ps.setDouble(2, i.getPrice());
            db.rs = db.ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.dispose();
        }
    }

    @Override
    public boolean removeItemById(int id) {
        DatabaseConnection db = new DatabaseConnection();
        db.connect();
        try {
            db.ps = db.conn.prepareStatement(SQL.removeItemById.getQuery());
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
