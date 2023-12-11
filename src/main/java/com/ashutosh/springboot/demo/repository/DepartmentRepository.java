package com.ashutosh.springboot.demo.repository;

import com.ashutosh.springboot.demo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    /* we don't need to implement it fully by the naming convention findBy[What we are looking for]
       we would be able to get the data

     */


    public Department findByDepartmentName(String departmentName);

    //obtaining tasks through SQL query by the function
    //@Query(value = "", nativeQuery = true)
    //refer documentation for more details
    public Department findByDepartmentNameIgnoreCase(String departmentName);

    void deleteById(Long departmentId);
}
