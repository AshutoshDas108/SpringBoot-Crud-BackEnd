package com.ashutosh.springboot.demo.repository;

import com.ashutosh.springboot.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        //Mock data getting saved into the database temporarily
        Department department = Department.builder()
                .departmentName("Mechanical Engineering")
                .getDepartmentCode("ME-01")
                .departmentAddress("Delhi")
                .build();

        testEntityManager.persist(department);
    }

    @Test
    public void whenFindById_then_ReturnDepartment(){
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "Mechanical Engineering");
    }
}