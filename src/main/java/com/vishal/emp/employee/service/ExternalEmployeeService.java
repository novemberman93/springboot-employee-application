package com.vishal.emp.employee.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExternalEmployeeService {

    public String callExternalService() {
        // Simulate failure
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Failed to fetch data");
        }
        return "Employee data from external service";
    }
}
