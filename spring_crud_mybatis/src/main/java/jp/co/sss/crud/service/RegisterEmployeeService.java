package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.mapper.EmployeeMapper;
import jp.co.sss.crud.util.BeanCopy;

/**
 * 社員登録サービス
 * 
 */
@Service
public class RegisterEmployeeService {
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * 社員登録
	 * 
	 * @return true 登録が成功した場合
	 */
	public Boolean execute(EmployeeForm employeeForm) {
		Employee employee = BeanCopy.copyFormToEmployee(employeeForm);

		return mapper.insert(employee);
	}

}
