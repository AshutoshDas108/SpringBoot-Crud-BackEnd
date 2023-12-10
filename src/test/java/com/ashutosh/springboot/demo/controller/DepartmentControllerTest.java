package com.ashutosh.springboot.demo.controller;

import com.ashutosh.springboot.demo.entity.Department;
import com.ashutosh.springboot.demo.error.DepartmentNotFoundException;
import com.ashutosh.springboot.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //mocking the service layer since we will be using it
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department= Department.builder()
                .departmentAddress("Hyderabad")
                .departmentName("IT")
                .getDepartmentCode("IT-09")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment= Department.builder()
                .departmentAddress("Hyderabad")
                .departmentName("IT")
                .getDepartmentCode("IT-09")
                .build();

        //mocking data
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        //calling end point
        //we can remove these class names by adding on demand static import
          //mockMvc.perform(MockMvcRequestBuilders.post("/departments") is replaced with:

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\" : \"IT\",\n" +
                        "\t\"departmentAddress\" : \"Hyderabad\",\n" +
                        "\t\"getDepartmentCode\" : \"IT-09\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);
        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").
                        value(department.getDepartmentName()));
             //just Like Name other values can be matched for varification
    }
}