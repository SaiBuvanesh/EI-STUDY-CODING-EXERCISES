package StructuralDesignPatternUsecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Employee {
    void showEmployeeDetails();
}

class IndividualEmployee implements Employee {
    private String name;
    private String position;

    public IndividualEmployee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println("Employee: " + name + ", Position: " + position);
    }
}

class Manager implements Employee {
    private String name;
    private List<Employee> subordinates;

    public Manager(String name) {
        this.name = name;
        this.subordinates = new ArrayList<>();
    }

    public void add(Employee employee) {
        subordinates.add(employee);
    }

    public void remove(Employee employee) {
        subordinates.remove(employee);
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println("Manager: " + name);
        for (Employee employee : subordinates) {
            employee.showEmployeeDetails();
        }
    }
}

public class CompositePattern{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter individual employee name: ");
        String emp1Name = scanner.nextLine();
        System.out.print("Enter individual employee position: ");
        String emp1Position = scanner.nextLine();
        IndividualEmployee emp1 = new IndividualEmployee(emp1Name, emp1Position);

        System.out.print("Enter another individual employee name: ");
        String emp2Name = scanner.nextLine();
        System.out.print("Enter another individual employee position: ");
        String emp2Position = scanner.nextLine();
        IndividualEmployee emp2 = new IndividualEmployee(emp2Name, emp2Position);

        System.out.print("Enter manager name: ");
        String managerName = scanner.nextLine();
        Manager manager = new Manager(managerName);
        manager.add(emp1);
        manager.add(emp2);

        System.out.print("Enter director name: ");
        String directorName = scanner.nextLine();
        Manager director = new Manager(directorName);
        director.add(manager);

        director.showEmployeeDetails();

        scanner.close();
    }
}
