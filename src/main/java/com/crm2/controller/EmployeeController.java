package com.crm2.controller;

import com.crm2.entity.Employee;
import com.crm2.payload.EmployeeDto;
import com.crm2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    //create method to add employee
//    @PostMapping("/add")
//    public String addEmployee(
//            @RequestBody Employee employee
//            ){
////        System.out.println(employee.getName());
////        System.out.println(employee.getEmailId());
////        System.out.println(employee.getMobile());
//        employeeService.addEmployee(employee);
//        return "Saved...";
//    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addEmployee(
//            @RequestBody Employee employee
//    ){
//        employeeService.addEmployee(employee);
//        return new ResponseEntity<>("saved...", HttpStatus.CREATED);
//    }

//@PostMapping("/add")
//public ResponseEntity<Employee> addEmployee(
//        @RequestBody Employee employee
//){
//    Employee emp = employeeService.addEmployee(employee);
//    return new ResponseEntity<>(emp, HttpStatus.CREATED);
//}

    //http://localhost:8080/api/v1/employee?id=3
//    @DeleteMapping
//    public String deleteEmployee(@RequestParam Long id){
//        employeeService.deleteEmployee(id);
//        return "Successfully deleted....";
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
          @Valid @RequestBody EmployeeDto dto,
          BindingResult result
    ){
        if (result.hasErrors()){
            return new  ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    //http://localhost:8080/api/v1/employee?id=1
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Deleted...", HttpStatus.OK);
    }


    //http://localhost:8080/api/v1/employee?id=1
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployeee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto
            ) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
        }

   //http://localhost:8080/api/v1/employee?pageNo=0&pageSize=7&sortBy=emailId&sortDir=desc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(name = "pageSize",required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "pageNo",required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "sortBy",required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",required = false, defaultValue = "asc") String sortDir


    ) {
       List<EmployeeDto>  employeeDto = employeeService.getEmployee(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/employee/employeeId/2
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeesById(
            @PathVariable long empId
    ) {
        EmployeeDto dto =  employeeService.getEmployeesById(empId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
