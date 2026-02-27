package jp.co.sss.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.mapper.EmployeeMapper;

@Service
public class SearchForEmployeesByEmpNameService {
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * 社員名検索表示
	 * 
	 * @return 社員エンティティ
	 */
	public List<Employee> execute(String empName) {
		return mapper.findByEmpNameContainingOrderByEmpId(empName);
	}

}
