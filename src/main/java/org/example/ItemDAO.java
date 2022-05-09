package org.example;

import java.util.List;

public interface ItemDAO {
    enum SQL {
        getAllItems("select * from items"),
        addItem("insert into items (name, price) values (?, ?)"),
        removeItemById("delete from items where id = ?");
        String query;

        public String getQuery() {

            return query;
        }

        SQL(String query) {

            this.query = query;
        }
    }
    List<Items> getAllItems();
    boolean addItem(Items i);
    boolean removeItemById(int id);
}
