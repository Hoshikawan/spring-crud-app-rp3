package jp.co.sss.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.mapper.EmployeeMapper;

@Service
public class SearchForEmployeesByDepartmentService {
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * 社員名表示
	 * 
	 * @return 社員エンティティ
	 */
	public List<Employee> execute(Integer deptId) {
		return mapper.findByDepartmentDeptIdOrderByEmpId(deptId);
	}

}
