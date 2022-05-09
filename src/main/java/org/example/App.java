package org.example;

import java.util.Scanner;

public class App 
{
    public static Scanner reader = new Scanner(System.in);

    static void menu(){
            System.out.println("Make a selection:");
            System.out.println("1. Display all customers");
            System.out.println("2. Display all items");
            System.out.println("3. Add a customer");
            System.out.println("4. Add an item");
            System.out.println("5. Get customer details");
            System.out.println("6. Remove customer");
            System.out.println("7. Remove item");
            System.out.println("8. Exit");
    }

    public static void main( String[] args )
    {
        // create CustomerService
        CustomerService cs = new CustomerService();
        ItemService is = new ItemService();
        System.out.println( "Running Main Method" );
        int choice = 0;
        while(choice != 8) {
            if (choice >= 0 && choice < 9) {
                menu();
            }
            choice = reader.nextInt();
            switch (choice) {
                case 1:
                    // Will run getALlCustomers and display results
                    cs.displayAllCustomers();
                    break;
                case 2:
                    // Will run getAllItems and display results
                    is.displayAllItems();
                    break;
                case 3:
                    // Add new customer to db
                    reader.nextLine();
                    Customers customer = new Customers();
                    System.out.print("\nEnter First Name:\n");
                    customer.setFname(reader.nextLine());
                    System.out.print("\nEnter Last Name:\n");
                    customer.setLname(reader.nextLine());
                    System.out.print("\nEnter Email\n");
                    customer.setEmail(reader.nextLine());
                    if (cs.addCustomer(customer)) {
                        System.out.printf("%s added successfully!%n", customer.getFname());
                    } else {
                        System.out.printf("Failed to add %s, try again!%n", customer.getFname());
                    }
                    break;
                case 4:
                    // Add new item to db
                    reader.nextLine();
                    Items item = new Items();
                    System.out.print("\nEnter Item Name:\n");
                    item.setName(reader.nextLine());
                    System.out.print("\nEnter Item Price:\n");
                    item.setPrice(reader.nextDouble());
                    if (is.addItem(item)) {
                        System.out.printf("%s added successfully!%n", item.getName());
                    } else {
                        System.out.printf("Failed to add %s, try again!%n", item.getName());
                    }
                    break;
                case 5:
                    // Get details for a customer with id
                    cs.displayAllCustomers();
                    reader.nextLine();
                    System.out.print("\nEnter customer id to review\n");
                    int id = reader.nextInt();
                    Customers c = cs.getCustomerById(id);
                    if ( c != null) {
                        cs.header("Customer Details");
                        System.out.printf("Name: %s %s%nE-mail: %s%n", c.getFname(), c.getLname(), c.getEmail());

                    } else {
                        System.out.println("Unable to locate customer");
                    }
                    break;
                case 6:
                    // Remove customer from db by id
                    reader.nextLine();
                    cs.displayAllCustomers();
                    System.out.print("\nEnter customer id\n");
                    if (cs.removeCustomerById(reader.nextInt())) {
                        System.out.println("Customer removed successfully");
                    } else {
                        System.out.println("Customer not found, please try again");
                    }
                    break;
                case 7:
                    // Remove item from db by id
                    reader.nextLine();
                    System.out.print("\nEnter item id\n");
                    if (is.removeItemById(reader.nextInt())) {
                        System.out.println("Item removed successfully");
                    } else {
                        System.out.println("Item not found, please try again");
                    }
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;
            }
        }
        reader.close();
    }
}
