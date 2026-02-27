package jp.co.sss.crud.entity;

import java.util.List;

import lombok.Data;

@Data
public class Department {

	private Integer deptId;

	private String deptName;

	private List<Employee> employeeList;

}
