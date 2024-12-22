package com.crm2.service;
import com.crm2.entity.Employee;
import com.crm2.exception.ResourceNotFound;
import com.crm2.payload.EmployeeDto;
import com.crm2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

   // @Autowired
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

//    public Employee addEmployee(Employee employee) {
//        Employee emp = employeeRepository.save(employee);
//        return emp;
//    }

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        employeeDto.setDate(new Date());

        return employeeDto;
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){
//        Optional<Employee> optEmp = employeeRepository.findById(id);
//        Employee employee = optEmp.get();
//        employee.setName(dto.getName());
//        employee.setMobile(dto.getMobile());
//        employee.setEmailId(dto.getEmailId());

        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;
    }

    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir)
    {
        //we have used here turnary conditions.
       Sort sort =  sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable page =  PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee){
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;

//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setMobile(employee.getMobile());
//        dto.setEmailId(employee.getEmailId());
//        return dto;
    }

    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);
        return emp;

//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setMobile(dto.getMobile());
//        emp.setEmailId(dto.getEmailId());
//        return emp;
    }

    public EmployeeDto getEmployeesById(Long empId) {
//        Optional<Employee> opEmp = employeeRepository.findById(empId);
//        Employee employee = opEmp.get();
//        return mapToDto(employee);
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Record not found with id " + empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}
