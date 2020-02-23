package com.ali.hyacinth.ims;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ali.hyacinth.ims.exceptions.InvalidInputException;
import com.ali.hyacinth.ims.model.Customer;
import com.ali.hyacinth.ims.model.Employee;
import com.ali.hyacinth.ims.model.Transaction;

@SpringBootApplication
public class ImsBackendApplication {
	
	private static List<Employee> currentEmployees; 
	private static List<Customer> currentCustomers; 
	private static List<Transaction> currentTransactions; 

	public static void main(String[] args) {
		
		SpringApplication.run(ImsBackendApplication.class, args);
	}
	
	private static void init() {
		currentEmployees = new ArrayList<Employee>();
		currentCustomers = new ArrayList<Customer>();
		currentTransactions = new ArrayList<Transaction>();
	}
	
	public static java.util.Date getCurrentDate() {
	    java.util.Calendar cal = java.util.Calendar.getInstance();
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date date = cal.getTime();
	    return date;
	  }

	public static List<Employee> getCurrentEmployees() {
		return currentEmployees;
	}

	public static void addCurrentEmployee(Employee currenteEmployee) {
		if (currentEmployees == null) {
			init();
			currentEmployees.add(currenteEmployee);
		} else {
			currentEmployees.add(currenteEmployee);
		}
		
	}
	
	public static void removeCurrentEmployee(String userName) {
		Employee employee = null;
		for (Employee currentEmployee : getCurrentEmployees()) {
			if (currentEmployee.getUserName().equals(userName)) {
				employee = currentEmployee;
			}
		}
		if (employee == null) {
			throw new InvalidInputException("The employee is not currently logged in");
		}
		getCurrentEmployees().remove(employee);
	}
	
	public static List<Customer> getCurrentCustomers() {
		return currentCustomers;
	}
	
	public static void addCurrentCustomer(Customer currenteCustomer) {
		if (currentCustomers == null) {
			init();
			currentCustomers.add(currenteCustomer);
		} else {
			currentCustomers.add(currenteCustomer);
		}
	}
	
	public static void removeCurrentCustomer(String customerUserName) {
		Customer customer = null;
		for (Customer currentCustomer : getCurrentCustomers()) {
			if (currentCustomer.getUserName().equals(customerUserName)) {
				customer = currentCustomer;
			}
		}
		getCurrentCustomers().remove(customer);
	}
	

	public static List<Transaction> getCurrentTransactions() {
		return currentTransactions;
	}

	
	public static void addCurrentTransaction(Transaction currentTransaction) {currentTransactions.add(currentTransaction);
		if (currentTransactions == null) {
			init();
			currentTransactions.add(currentTransaction);
		} else {
			currentTransactions.add(currentTransaction);
		}
		
	}
	
	public void removeCurrentTransaction(long transactionID) {
		Transaction transaction = null;
		for (Transaction currentTransaction : getCurrentTransactions()) {
			if (currentTransaction.getId() == transactionID) {
				transaction = currentTransaction;
			}
		}
		getCurrentCustomers().remove(transaction);
	}

	public static void setCurrentEmployees(List<Employee> currentEmployees) {
		ImsBackendApplication.currentEmployees = currentEmployees;
	}

	public static void setCurrentCustomers(List<Customer> currentCustomers) {
		ImsBackendApplication.currentCustomers = currentCustomers;
	}

	public static void setCurrentTransactions(List<Transaction> currentTransactions) {
		ImsBackendApplication.currentTransactions = currentTransactions;
	}

}
