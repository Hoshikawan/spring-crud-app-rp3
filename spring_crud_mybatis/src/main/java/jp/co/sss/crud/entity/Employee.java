package jp.co.sss.crud.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {

	private Integer empId;

	private String empPass;

	private String empName;

	private Integer gender;

	private String address;

	private Date birthday;

	private Integer authority;

	private Integer deptId;

	private String deptName;

}
