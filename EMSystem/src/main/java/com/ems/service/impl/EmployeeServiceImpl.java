package com.ems.service.impl;

import com.ems.entity.EmployeeEntity;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setEmailId(employee.getEmailId());
        
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        
        Employee result = new Employee();
        result.setId(savedEntity.getId());
        result.setFirstName(savedEntity.getFirstName());
        result.setLastName(savedEntity.getLastName());
        result.setEmailId(savedEntity.getEmailId());
        
        return result;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(entity -> {
            Employee employee = new Employee();
            employee.setId(entity.getId());
            employee.setFirstName(entity.getFirstName());
            employee.setLastName(entity.getLastName());
            employee.setEmailId(entity.getEmailId());
            return employee;
        }).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            Employee employee = new Employee();
            employee.setId(employeeEntity.getId());
            employee.setFirstName(employeeEntity.getFirstName());
            employee.setLastName(employeeEntity.getLastName());
            employee.setEmailId(employeeEntity.getEmailId());
            return employee;
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            employeeEntity.setFirstName(employee.getFirstName());
            employeeEntity.setLastName(employee.getLastName());
            employeeEntity.setEmailId(employee.getEmailId());
            
            EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);
            
            Employee result = new Employee();
            result.setId(updatedEntity.getId());
            result.setFirstName(updatedEntity.getFirstName());
            result.setLastName(updatedEntity.getLastName());
            result.setEmailId(updatedEntity.getEmailId());
            
            return result;
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
