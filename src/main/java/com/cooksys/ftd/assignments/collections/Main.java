package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.model.Manager;
import com.cooksys.ftd.assignments.collections.model.OrgChart;
import com.cooksys.ftd.assignments.collections.model.Worker;

public class Main {

    /**
     * TODO [OPTIONAL]: Students may use this main method to manually test their code. They can instantiate Employees
     *  and an OrgChart here and test that the various methods they've implemented work as expected. This class and
     *  method are purely for scratch work, and will not be graded.
     */
    public static void main(String[] args) {
    	
    	Manager headManager = new Manager("Will Martalla");
    	Manager manager1 = new Manager("Julio", headManager);
    	Manager manager2 = new Manager("Micheal", manager1);
    	Manager manager3 = new Manager("Some dude", manager2);
    	Worker employee1 = new Worker("Austin Howard", manager3);
    	Worker employee2 = new Worker("Brittany", manager3);
    	
    	
    	OrgChart employees = new OrgChart();
    	
    	System.out.println(employees.addEmployee(headManager));
    	System.out.println(employees.addEmployee(manager1));
    	System.out.println(employees.addEmployee(manager2));
    	System.out.println(employees.addEmployee(manager3));
    	System.out.println(employees.addEmployee(employee1));
    	System.out.println(employees.addEmployee(employee2));
//    	
//    	System.out.println(employee1.getChainOfCommand());
//    	
    	System.out.println(employees.getDirectSubordinates(manager3));
//    	System.out.println(employees.getAllEmployees());
//    	System.out.println(employees.getAllManagers());
    }
    
    

}
