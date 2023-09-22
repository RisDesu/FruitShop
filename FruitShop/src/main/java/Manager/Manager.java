/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Fruit;
import Entity.Order;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Manager {

    private static final Scanner scanner = new Scanner(System.in);

    public static int menu() {
        System.out.println("1. Create Fruit");
        System.out.println("2. View Orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        return Validation.checkInputIntLimit(1, 4);
    }

    public static void createFruit(ArrayList<Fruit> fruitList) {
        while (true) {
            System.out.print("Enter fruit id: ");
            String fruitId = Validation.checkInputString();

            if (!Validation.checkIdExist(fruitList, fruitId)) {
                System.err.println("ID already exists. Please choose a different ID.");
                continue;
            }

            System.out.print("Enter fruit name: ");
            String fruitName = Validation.checkInputString();

            System.out.print("Enter price: ");
            double price = Validation.checkInputDouble();

            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputInt();

            System.out.print("Enter origin: ");
            String origin = Validation.checkInputString();

            fruitList.add(new Fruit(fruitId, fruitName, price, quantity, origin));

            if (!Validation.checkInputYN()) {
                break;
            }
        }
    }

    public static void viewOrder(Hashtable<String, ArrayList<Order>> orderTable) {
        for (String customerName : orderTable.keySet()) {
            System.out.println("Customer: " + customerName);
            ArrayList<Order> orderList = orderTable.get(customerName);
            displayOrderList(orderList);
        }
    }

    public static void shopping(ArrayList<Fruit> fruitList, Hashtable<String, ArrayList<Order>> orderTable) {
        if (fruitList.isEmpty()) {
            System.err.println("No items available.");
            return;
        }

        ArrayList<Order> orderList = new ArrayList<>();

        while (true) {
            displayFruitList(fruitList);

            System.out.print("Enter item: ");
            int item = Validation.checkInputIntLimit(1, fruitList.size());

            Fruit selectedFruit = getFruitByItem(fruitList, item);

            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputIntLimit(1, selectedFruit.getQuantity());

            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);

            if (!Validation.checkItemExist(orderList, selectedFruit.getFruitId())) {
                updateOrder(orderList, selectedFruit.getFruitId(), quantity);
            } else {
                orderList.add(new Order(selectedFruit.getFruitId(), selectedFruit.getFruitName(),
                        quantity, selectedFruit.getPrice()));
            }

            if (!Validation.checkInputYN()) {
                break;
            }
        }

        displayOrderList(orderList);

        System.out.print("Enter customer name: ");
        String customerName = Validation.checkInputString();

        orderTable.put(customerName, orderList);

        System.err.println("Order added successfully.");
    }

    public static void displayFruitList(ArrayList<Fruit> fruitList) {
        int countItem = 1;

        System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");

        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f$\n", countItem++,
                        fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
            }
        }
    }

    public static Fruit getFruitByItem(ArrayList<Fruit> fruitList, int item) {
        int countItem = 1;

        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() != 0) {
                if (countItem == item) {
                    return fruit;
                }
                countItem++;
            }
        }

        return null;
    }

    public static void displayOrderList(ArrayList<Order> orderList) {
        double total = 0;

        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");

        for (Order order : orderList) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());

            total += order.getPrice() * order.getQuantity();
        }

        System.out.println("Total: " + total);
    }

    public static void updateOrder(ArrayList<Order> orderList, String id, int quantity) {
        for (Order order : orderList) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }
}

