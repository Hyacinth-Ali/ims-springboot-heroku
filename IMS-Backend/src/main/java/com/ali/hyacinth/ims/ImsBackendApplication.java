package com.ali.hyacinth.ims;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ali.hyacinth.ims.model.Employee;

@SpringBootApplication
public class ImsBackendApplication {
	
	private List<Employee> currentEmployees; 

	public static void main(String[] args) {
		SpringApplication.run(ImsBackendApplication.class, args);
	}

	public List<Employee> getCurrentEmployees() {
		return currentEmployees;
	}

	public void addCurrenteEmployees(Employee currenteEmployees) {
		this.currentEmployees.add(currenteEmployees);
	}
	
	public void removeCurrentEmployee(String employeeId) {
		Employee employee = null;
		for (Employee currentEmployee : this.getCurrentEmployees()) {
			if (currentEmployee.getEmployeeId().equals(employeeId)) {
				employee = currentEmployee;
			}
		}
		this.getCurrentEmployees().remove(employee);
	}
	


}
