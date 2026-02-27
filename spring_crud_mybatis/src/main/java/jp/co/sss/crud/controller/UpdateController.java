package jp.co.sss.crud.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.service.SearchForDepartmentByDeptIdService;
import jp.co.sss.crud.service.SearchForEmployeesByEmpIdService;
import jp.co.sss.crud.service.UpdateEmployeeService;
import jp.co.sss.crud.util.BeanCopy;
import jp.co.sss.crud.util.Constant;

@Controller
public class UpdateController {

	@Autowired
	UpdateEmployeeService updateEmployeeService;

	@Autowired
	SearchForEmployeesByEmpIdService searchForEmployeesByEmpIdService;

	@Autowired
	SearchForDepartmentByDeptIdService searchForDepartmentByDeptIdService;

	/**
	 * 社員情報の変更内容入力画面を出力
	 *
	 * @param empId
	 *            社員ID
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/input", method = RequestMethod.GET)
	public String inputUpdate(Integer empId, @ModelAttribute EmployeeForm employeeForm) {

		//empIdから検索した社員情報をformに積め直す
		BeanCopy.copyEntityToForm(searchForEmployeesByEmpIdService.execute(empId), employeeForm);
		return "update/update_input";
	}

	/**
	 * 社員情報の変更確認画面を出力
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @param model
	 *            モデル
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/check", method = RequestMethod.POST)
	public String checkUpdate(@Valid @ModelAttribute EmployeeForm employeeForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "update/update_input";
		} else {
			Department department = searchForDepartmentByDeptIdService.execute(employeeForm.getDeptId());
			model.addAttribute("deptName", department.getDeptName());

			return "update/update_check";
		}
	}

	/**
	 * 変更内容入力画面に戻る
	 *
	 * @param employeeForm 変更対象の社員情報
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/back", method = RequestMethod.POST)
	public String backInputUpdate(@ModelAttribute EmployeeForm employeeForm) {
		return "update/update_input";
	}

	/**
	 * 社員情報の変更実行
	 *
	 * @param employeeForm
	 *            変更対象の社員情報
	 * @return 完了画面URLへリダイレクト
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.POST)
	public String completeUpdate(EmployeeForm employeeForm, HttpSession session) {
		Employee employee = BeanCopy.copyFormToEmployee(employeeForm);

		if (employee.getAuthority() == null) {
			employee.setAuthority(Constant.DEFAULT_AUTHORITY);//一般権限をset
		}

		updateEmployeeService.execute(employee);

		// 自分の社員情報を変更した場合、セッション中の社員情報を最新の内容に上書き
		Employee user = (Employee) session.getAttribute("user");
		if (user.getEmpId() == employee.getEmpId()) {
			user.setEmpName(employee.getEmpName());
		}

		return "redirect:/update/complete";
	}

	/**
	 * 社員情報の変更完了画面
	 *
	 * @return 遷移先のビュー
	 */
	@RequestMapping(path = "/update/complete", method = RequestMethod.GET)
	public String completeUpdate() {

		return "update/update_complete";

	}

}
