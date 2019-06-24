package com.lhj1998.mybatis.generator.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenerateInfo {

    private List<String> table;

    private String pojo;

    private String mapper;

    private String mappers;

}
