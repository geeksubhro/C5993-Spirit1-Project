package com.railway.management.dao;

import com.railway.management.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

	void updateEmployee(Employee employee);
}
