package com.ali.hyacinth.ims;

import java.util.Calendar;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public void addCurrentEmployees(Employee currenteEmployee) {
		currentEmployees.add(currenteEmployee);
	}
	
	public void removeCurrentEmployee(String employeeId) {
		Employee employee = null;
		for (Employee currentEmployee : getCurrentEmployees()) {
			if (currentEmployee.getEmployeeId().equals(employeeId)) {
				employee = currentEmployee;
			}
		}
		getCurrentEmployees().remove(employee);
	}
	
	public static List<Customer> getCurrentCustomers() {
		return currentCustomers;
	}
	
	public void addCurrentCustomer(Customer currenteCustomer) {
		currentCustomers.add(currenteCustomer);
	}
	
	public void removeCurrentCustomer(String customerUserName) {
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

	
	public void addCurrentTransaction(Transaction currentTransaction) {
		currentTransactions.add(currentTransaction);
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
	
	
	


}
