/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import database.OrderDB;
import database.ProductDB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ahmad
 */
public class project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Hyper Market Management System\n");
        String username, password;
        char c;
        do {
            System.out.printf("\nMain Menu:\nLogin (Enter L)\nExit (Enter E)\n?:");
            c = input.nextLine().charAt(0);
            
            if (c == 'E')break;

            System.out.printf("\nEnter User-Name: ");
            username = input.nextLine();
            System.out.printf("Enter Password : ");
            password = input.nextLine();
            if (!userExsist(username, password)) {
                System.out.println("User Not Found!");
            }

            ArrayList<Employee> list = new ArrayList<>();
            list = EmployeeDB.get_employees();
            
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserName().equals(username) && list.get(i).getPassword().equals(password)) {
                    if (list.get(i).getEType().equals("A")) {
                        AdminEmployee admin = new AdminEmployee(list.get(i).getId(), list.get(i).getfName(), list.get(i).getlName(), list.get(i).getUserName(), list.get(i).getPassword(), list.get(i).getEType());
                        admin.openList();
                    }
                    if (list.get(i).getEType().equals("M")) {
                        MarktingEmployee marktingEmp = new MarktingEmployee(list.get(i).getId(), list.get(i).getfName(), list.get(i).getlName(), list.get(i).getUserName(), list.get(i).getPassword(), list.get(i).getEType());
                        marktingEmp.openList();
                    }
                    if (list.get(i).getEType().equals("I")) {
                        InventoryEmployee inventoryEmp = new InventoryEmployee(list.get(i).getId(), list.get(i).getfName(), list.get(i).getlName(), list.get(i).getUserName(), list.get(i).getPassword(), list.get(i).getEType());
                        inventoryEmp.openList();
                    }
                    if (list.get(i).getEType().equals("S")) {
                        SalesEmployee salesEmp = new SalesEmployee(list.get(i).getId(), list.get(i).getfName(), list.get(i).getlName(), list.get(i).getUserName(), list.get(i).getPassword(), list.get(i).getEType());
                        salesEmp.openList();
                    }
                }
            }

        } while (c == 'L');

        System.out.println("Exit!");
    }

    public static boolean userExsist(String username, String password) {
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(username) && list.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}