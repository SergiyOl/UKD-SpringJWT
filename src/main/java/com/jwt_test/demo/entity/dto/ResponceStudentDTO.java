package com.jwt_test.demo.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponceStudentDTO {
    private Long id;
    private String name;
    private int age;
}
