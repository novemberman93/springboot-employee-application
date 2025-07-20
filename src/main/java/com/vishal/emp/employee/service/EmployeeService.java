package com.vishal.emp.employee.service;

import com.vishal.emp.employee.entity.Employee;
import com.vishal.emp.employee.exception.EmployeeNotFoundException;
import com.vishal.emp.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepository;

    @Cacheable(value ="employees",key ="#id")
    public Employee getByEmpId(Long id) {
        return empRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);

    }

    @Transactional
    public Employee saveEmp(Employee emp) {
        return empRepository.save(emp);
    }

    @Transactional
    public List<Employee> saveAllEmp(@Valid List<Employee> emps) {
        return empRepository.saveAll(emps);
    }

    public List<Employee> getAllEmps() {
        return empRepository.findAll();
    }

    @Transactional
    @CacheEvict(value ="employees",key ="#id")
    public void deleteEmpByid(Long id) {
        empRepository.deleteById(id);
    }

    @CachePut(value ="employees",key ="#id")
    public Employee updateEmp(Long id, Employee emp) {
        Employee existing = empRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        if (existing != null) {
            existing.setName(emp.getName());
            existing.setDepartment(emp.getDepartment());
            existing.setSalary(emp.getSalary());
            return empRepository.save(existing);
        }

        return null;
    }

    public Employee patchEmp(Long id, Map<String, Object> updates) {
        Employee existing = empRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> existing.setName((String) value);
                case "department" -> existing.setDepartment((String) value);
                case "salary" -> existing.setSalary((long) Integer.parseInt(value.toString()));
            }
        });
        return empRepository.save(existing);
    }

    public List<Employee> getMaxSalaryEmployee() {
        return empRepository.getMaxSalaryEmp();
    }

    @Transactional
    public Employee incrementSalary(Long id, Long salary) {
        int rowsUpdated = empRepository.incrementSalary(id, salary);
        if (rowsUpdated > 0) {
            return empRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        } else {
            throw new RuntimeException("Salary update failed for id: " + id);
        }
    }

    public List<Employee> getSecondHighestSalary() {
        return empRepository.getSecondHighestSalary();
    }

}
