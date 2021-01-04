/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import database.EmployeeDB;
import database.PreviousActionsDB;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminEmployee extends Employee {

    public AdminEmployee(int id, String fName, String lName, String userName, String password) {
        super(id, fName, lName, userName, password, "A");
    }

    public AdminEmployee(String fName, String lName, String userName, String password) {
        super(fName, lName, userName, password, "A");
    }

    @Override
    public String getTitle() {
        return "AD." + super.getTitle();
    }

    public int openList() {
        Scanner input = new Scanner(System.in);
        char c;
        System.out.println("\nHello ," + this.getfName() + "!\n");
        do {
            System.out.printf("\nAdminstration Menu:"
                    + "\nAdd a new employee.                 (Enter 1)"
                    + "\nDelete an employee.                 (Enter 2)"
                    + "\nUpdate an employee information.     (Enter 3)"// all except id
                    + "\nlist all employees.                 (Enter 4)"// all shown
                    + "\nSearch for an employee.             (Enter 5)"// Search id or username // print value only
                    + "\nAlter your general information.     (Enter 6)"
                    + "\nAlter your User-Name and Password.  (Enter 7)"
                    + "\nDisplay all your previous actions.  (Enter 8)"
                    + "\nLogOut                              (Enter 9)\n");
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':
                    Add_new_employee();
                    break;
                case '2':
                    Delete_an_employee();
                    break;
                case '3':
                    Update_an_employee_information();
                    break;
                case '4':
                    list_all_employees();
                    break;
                case '5':
                    search();
                    break;
                case '6':
                    update_general_info();
                    break;
                case '7':
                    update_userName_password();
                    break;
                case '8':
                    displayPreviousActions();
                    break;
            }

        } while (c != '9');
        System.out.println("bey bey ," + this.getfName() + "!\n");
        return 0;
    }

    private void Add_new_employee() {
        String fName;
        String lName;
        String userName;
        String password;
        String eType = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee's first name: ");
        fName = input.nextLine();
        System.out.print("Enter employee's last name: ");
        lName = input.nextLine();
        System.out.print("Enter employee's username: ");
        userName = input.nextLine();
        System.out.print("Enter employee's password: ");
        password = input.nextLine();
        char c;
        do {
            System.out.printf("\nEmployee Type:"
                    + "\nAminstration Departement.   (Enter 1)"
                    + "\nMarkting Departement.       (Enter 2)"
                    + "\nInventory Departement.      (Enter 3)"
                    + "\nSales Departement.          (Enter 4)\n"
            );
            System.out.printf("?: ");
            c = input.nextLine().charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.println("Invaild Input!");
            }

            switch (c) {
                case '1':
                    eType = "A";
                    break;
                case '2':
                    eType = "M";
                    break;
                case '3':
                    eType = "I";
                    break;
                case '4':
                    eType = "S";
                    break;
            }
        } while (c != '1' && c != '2' && c != '3' && c != '4');

        if (eType.equals("A")) {
            EmployeeDB.add_employee(new AdminEmployee(fName, lName, userName, password));
            Util.registerAction(this.getId(), "Add-Admin_Employee:(" + fName + ").");
        } else if (eType.equals("M")) {
            EmployeeDB.add_employee(new MarktingEmployee(fName, lName, userName, password));
            Util.registerAction(this.getId(), "Add-Markting_Employee:(" + fName + ").");
        } else if (eType.equals("S")) {
            EmployeeDB.add_employee(new SalesEmployee(fName, lName, userName, password));
            Util.registerAction(this.getId(), "Add-Sales_Employee:(" + fName + ").");
        } else if (eType.equals("I")) {
            EmployeeDB.add_employee(new InventoryEmployee(fName, lName, userName, password));
            Util.registerAction(this.getId(), "Add-Inventory_Employee:(" + fName + ").");
        }
        System.out.println("\nAdded!\n");
    }

    private void Delete_an_employee() {
        int id;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the id of the employee you want to delete: ");
        id = input.nextInt();
        EmployeeDB.delete_employee(id);
        System.out.println("\nDeleted!\n");
        Util.registerAction(this.getId(), "Delete-Employee ID:(" + id + ").");

    }

    private void Update_an_employee_information() {
        int id;
        String fName;
        String lName;
        //String fullName;
        String userName;
        String password;
        String eType = "";
        Scanner input = new Scanner(System.in);
        System.out.print("Enter employee's id: ");
        id = input.nextInt();
        if (EmployeeDB.isExisit(id)) {
            System.out.print("Enter employee's first name: ");
            fName = input.next();
            System.out.print("Enter employee's last name: ");
            lName = input.next();
            //fullName=fName+lName;
            System.out.print("Enter employee's username: ");
            userName = input.next();
            System.out.print("Enter employee's password: ");
            password = input.next();
            char c;
            do {
                System.out.printf("\nEmployee Type:"
                        + "\nAminstration Departement.   (Enter 1)"
                        + "\nMarkting Departement.       (Enter 2)"
                        + "\nInventory Departement.      (Enter 3)"
                        + "\nSales Departement.          (Enter 4)\n"
                );
                System.out.printf("?: ");
                c = input.nextLine().charAt(0);

                if (c != 1 && c != 2 && c != 3 && c != 4) {
                    System.out.println("Invaild Input!");
                }

                switch (c) {
                    case '1':
                        eType = "A";
                        break;
                    case '2':
                        eType = "M";
                        break;
                    case '3':
                        eType = "I";
                        break;
                    case '4':
                        eType = "S";
                        break;
                }
            } while (c != '1' && c != '2' && c != '3' && c != '4');
            EmployeeDB.update_employee(id, fName, lName, userName, password, eType);
            System.out.println("\nUpdated!\n");
            Util.registerAction(this.getId(), "Update-Employee ID:(" + id + ").");
        } else {
            System.out.println("\nNot Found!");
        }
    }

    private void list_all_employees() {
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
        System.out.printf("\n%-5s %-20s %-15s %-15s %-15s %-15s %-10s \n", "ID", "Title", "First Name", "Last Name", "User Name", "Password", "Employee Type");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof AdminEmployee) {
                AdminEmployee emp = (AdminEmployee) list.get(i);
                System.out.printf("%-5d %-20s %-15s %-15s %-15s %-15s %-10s \n", emp.getId(), emp.getTitle(), emp.getfName(), emp.getlName(), emp.getUserName(), emp.getPassword(), emp.getEType());
            } else if (list.get(i) instanceof SalesEmployee) {
                SalesEmployee emp = (SalesEmployee) list.get(i);
                System.out.printf("%-5d %-20s %-15s %-15s %-15s %-15s %-10s \n", emp.getId(), emp.getTitle(), emp.getfName(), emp.getlName(), emp.getUserName(), emp.getPassword(), emp.getEType());
            } else if (list.get(i) instanceof MarktingEmployee) {
                MarktingEmployee emp = (MarktingEmployee) list.get(i);
                System.out.printf("%-5d %-20s %-15s %-15s %-15s %-15s %-10s \n", emp.getId(), emp.getTitle(), emp.getfName(), emp.getlName(), emp.getUserName(), emp.getPassword(), emp.getEType());
            } else if (list.get(i) instanceof InventoryEmployee) {
                InventoryEmployee emp = (InventoryEmployee) list.get(i);
                System.out.printf("%-5d %-20s %-15s %-15s %-15s %-15s %-10s \n", emp.getId(), emp.getTitle(), emp.getfName(), emp.getlName(), emp.getUserName(), emp.getPassword(), emp.getEType());
            }
        }
    }

    void update_general_info() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter the new first name : ");
        String fname = input.next();
        System.out.printf("Enter the new last name : ");
        String lname = input.next();
        EmployeeDB.update_employee(this.getId(), fname, lname, this.getUserName(), this.getPassword(), this.getEType());
        System.out.println("\nUpdated!\n");
        Util.registerAction(this.getId(), "Update-Your First-Name & Last-Name.");
    }

    void update_userName_password() {

        Scanner input = new Scanner(System.in);
        System.out.printf("Enter user name : ");
        String username = input.next();
        System.out.printf("Enter password : ");
        String password = input.next();
        EmployeeDB.update_employee(this.getId(), this.getfName(), this.getlName(), username, password, this.getEType());
        System.out.println("\nUpdated!\n");
        Util.registerAction(this.getId(), "Update-Your User-Name & Password.");
    }

    public void search() {
        Scanner input = new Scanner(System.in);
        ArrayList<Employee> list = new ArrayList<>();
        list = EmployeeDB.get_employees();
        System.out.printf("Enter id : ");
        int id = input.nextInt();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                System.out.println("id : \t" + list.get(i).getId());
                System.out.println("first name : \t" + list.get(i).getfName());
                System.out.println("last name : \t" + list.get(i).getlName());
                System.out.println("user name : \t" + list.get(i).getUserName());
                System.out.println("password : \t" + list.get(i).getfName());
                System.out.println("employee type : " + list.get(i).getEType());

            }
        }

    }

    private void displayPreviousActions() {
        ArrayList<Action> list = PreviousActionsDB.get_actions(this.getId());
        Util.PrintActionHeader();
        for (int i = 0; i < list.size(); i++) {
            Util.PrintAction(list.get(i));
        }
    }

}
