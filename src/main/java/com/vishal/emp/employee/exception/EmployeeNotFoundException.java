package com.vishal.emp.employee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(){
        super("Employee not Found");
    }

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
