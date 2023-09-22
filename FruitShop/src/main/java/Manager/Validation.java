/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Entity.Fruit;
import Entity.Order;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Validation {

    private static final Scanner in = new Scanner(System.in);

    public static int checkInputIntLimit(int min, int max) {
        while (true) {
            int result = Integer.parseInt(in.nextLine().trim());
            if (result < min || result > max) {
                System.err.println("Please input a number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static String checkInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static int checkInputInt() {

        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Must be input integer.");
                System.out.print("Enter again: ");
            }
        }
    }

    public static double checkInputDouble() {
        while (true) {
            double result = Double.parseDouble(in.nextLine());
            if (result < 0) {
                System.err.println("Please input a positive number.");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkInputYN() {
        System.out.print("Do you want to continue (Y/N)? ");
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkIdExist(ArrayList<Fruit> lf, String id) {
        for (Fruit fruit : lf) {
            if (id.equalsIgnoreCase(fruit.getFruitId())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkItemExist(ArrayList<Order> lo, String id) {
        for (Order order : lo) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }
}
