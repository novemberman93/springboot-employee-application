package com.vishal.emp.employee.controller;

import com.vishal.emp.employee.entity.Employee;
import com.vishal.emp.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee emp = empService.getByEmpId(id);
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> emps = empService.getAllEmps();
        return ResponseEntity.status(HttpStatus.OK).body(emps);
    }

    @PostMapping("/save")
    public Employee saveEmployee(@Valid @RequestBody Employee emp) {
        return empService.saveEmp(emp);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Employee>> saveAllEmployee(@RequestBody @Valid List<@Valid Employee> emps) {
        List<Employee> empList = empService.saveAllEmp(emps);
        return ResponseEntity.status(HttpStatus.CREATED).body(empList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        empService.deleteEmpByid(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete successful");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        Employee updatedEmp = empService.updateEmp(id, emp);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmp);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Employee updatedEmp = empService.patchEmp(id, updates);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmp);
    }

    @GetMapping("/maxSalaryEmployee")
    public ResponseEntity<List<Employee>> maxSalaryEmployee() {
        List<Employee> emp = empService.getMaxSalaryEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @PatchMapping("/salaryIncrement/{id}")
    public ResponseEntity<Employee> salaryIncrement(@PathVariable Long id, @RequestParam Long salary) {
        try {
            Employee emp = empService.incrementSalary(id, salary);
            return ResponseEntity.ok(emp);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
