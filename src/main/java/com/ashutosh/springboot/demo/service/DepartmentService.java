package com.ashutosh.springboot.demo.service;

import com.ashutosh.springboot.demo.entity.Department;
import com.ashutosh.springboot.demo.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    Department updateDepartment(Long departmentId, Department department) throws DepartmentNotFoundException;

    Department fetchDepartmentByName(String departmentName) throws DepartmentNotFoundException;
}
