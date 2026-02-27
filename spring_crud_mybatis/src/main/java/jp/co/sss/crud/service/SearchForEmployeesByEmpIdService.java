package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.mapper.EmployeeMapper;

@Service
public class SearchForEmployeesByEmpIdService {
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * 社員ID検索
	 * 
	 * @return 社員エンティティ
	 */
	public Employee execute(Integer empId) {
		return mapper.findByEmpId(empId);
	}

}
