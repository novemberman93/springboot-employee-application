package com.vishal.emp.employee.service;

import com.vishal.emp.employee.entity.Employee;
import com.vishal.emp.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository empRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void testGetByEmpId(){
        Employee mockEmployee = new Employee(56L,"Pink","HR",50000L);

        Mockito.when(empRepository.findById(56l)).thenReturn(Optional.of(mockEmployee));
        Employee result = employeeService.getByEmpId(56l);

        assertNotNull(result);
        assertEquals("Pink",result.getName());
        assertEquals("HR",result.getDepartment());
        assertEquals(56000L,result.getSalary());

    }
}
