package com.ashutosh.springboot.demo.service;

import com.ashutosh.springboot.demo.entity.Department;
import com.ashutosh.springboot.demo.error.DepartmentNotFoundException;
import com.ashutosh.springboot.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException {
       Optional <Department> deptDb = departmentRepository.findById(departmentId);
        if(! deptDb.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found!");
        }
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException {
         Optional <Department> dept = departmentRepository.findById(departmentId);

        if(!dept.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Found!");
        }

        Department deptDB = dept.get();

         if(Objects.nonNull(department.getDepartmentName()) &&
            !"".equalsIgnoreCase(department.getDepartmentName())){
             deptDB.setDepartmentName(department.getDepartmentName());
         }

        if(Objects.nonNull(department.getGetDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getGetDepartmentCode())){
            deptDB.setGetDepartmentCode(department.getGetDepartmentCode());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            deptDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(deptDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) throws DepartmentNotFoundException {
        Department deptDb = departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
        if(! Objects.nonNull(deptDb)){
            throw new DepartmentNotFoundException("Department Not Found");
        }
        return deptDb;
    }
}
