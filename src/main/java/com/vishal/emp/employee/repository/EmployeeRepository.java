package com.vishal.emp.employee.repository;

import com.vishal.emp.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select emp from Employee emp where emp.salary =(select max(e.salary) from Employee e)")
    List<Employee> getMaxSalaryEmp();// For multiple employees with same salary

    @Modifying
    @Query("update Employee emp  set emp.salary = :salary  where emp.id = :id")
    int incrementSalary(@Param("id") Long id, @Param("salary") Long salary);
}
