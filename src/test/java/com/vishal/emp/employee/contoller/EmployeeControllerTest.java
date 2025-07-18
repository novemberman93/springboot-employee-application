package com.vishal.emp.employee.contoller;

import com.vishal.emp.employee.controller.EmployeeController;
import com.vishal.emp.employee.entity.Employee;
import com.vishal.emp.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetEmployeeById() throws Exception{
        // Given
        Employee e1= new Employee(56L,"Pink","HR",50000L);
        when(employeeService.getByEmpId(56L)).thenReturn(e1);
        // When + Then
        mockMvc.perform(get("/employee/56")
                .with(httpBasic("vishal", "3939")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pink"))
                .andExpect(jsonPath("$.department").value("HR"))
                .andExpect(jsonPath("$.salary").value("50000"));

    }
}
