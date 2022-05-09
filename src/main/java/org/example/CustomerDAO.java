package org.example;

import java.util.List;

public interface CustomerDAO {
    enum SQL {
        getAllCustomers("select * from customers"),
        getCustomerById("select * from customers where id = ?"),
        addCustomer("insert into customers (fname, lname, email) values (?,?,?)"),
        removeCustomerById("delete from customers where id = ?");

        String query;

        public String getQuery() {

            return query;
        }

        SQL(String query) {

            this.query = query;
        }
    }
    List<Customers> getAllCustomers();
    Customers getCustomerById(int id);
    boolean addCustomer(Customers c);
    boolean removeCustomerById(int id);

}
