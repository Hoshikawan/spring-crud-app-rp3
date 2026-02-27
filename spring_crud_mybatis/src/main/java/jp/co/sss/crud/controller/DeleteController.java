package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.service.DeleteEmployeeService;
import jp.co.sss.crud.service.SearchForDepartmentByDeptIdService;
import jp.co.sss.crud.service.SearchForEmployeesByEmpIdService;

@Controller
public class DeleteController {

	@Autowired
	DeleteEmployeeService deleteEmployeeService;
	@Autowired
	SearchForEmployeesByEmpIdService searchForEmployeesByEmpIdService;

	@Autowired
	SearchForDepartmentByDeptIdService searchForDepartmentByDeptIdService;

	/**
	 * 社員情報の削除内容確認画面を出力
	 *
	 * @param empId 社員ID
	 * @param model モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/delete/check", method = RequestMethod.GET)
	public String checkDelete(Integer empId, Model model) {
		Employee employee = searchForEmployeesByEmpIdService.execute(empId);
		model.addAttribute("employee", employee);
		model.addAttribute("deptName", employee.getDeptName());
		return "delete/delete_check";
	}

	/**
	 * 社員情報の削除完了画面を出力
	 *
	 * @param empId 社員ID
	 * @param model モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/delete/complete", method = RequestMethod.POST)
	public String completeDelete(Integer empId) {
		deleteEmployeeService.execute(empId);
		return "redirect:/delete/complete";
	}

	/**
	 * 社員情報の削除完了画面を出力
	 *
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/delete/complete", method = RequestMethod.GET)
	public String completeDelete() {
		return "delete/delete_complete";

	}

}
