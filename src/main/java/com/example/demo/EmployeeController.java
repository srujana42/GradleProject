package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000",maxAge = 3600)
@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getAllEmployeeById(@PathVariable(value = "id") Integer id) {
        return employeeRepository.findById(id);
    }

    @PostMapping("/employees")
    public @Valid Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public @Valid Employee UpdateEmployee(@PathVariable(value = "id") Integer id, @Valid @RequestBody Employee employee)
            throws EmployeeNotFoundException {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        emp.setName(employee.getName());
        Employee updEmployee = employeeRepository.save(emp);
        return updEmployee;
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") int Id) throws EmployeeNotFoundException {
        Employee emp1 = employeeRepository.findById(Id)
                .orElseThrow(() -> new EmployeeNotFoundException(Id));

        employeeRepository.delete(emp1);
        System.out.println("test1234589987776");


        return ResponseEntity.ok().build();
    }


}

