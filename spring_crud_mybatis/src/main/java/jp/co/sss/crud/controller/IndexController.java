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

import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.service.LoginResult;
import jp.co.sss.crud.service.LoginService;

@Controller
public class IndexController {

	@Autowired
	HttpSession session;

	@Autowired
	LoginService loginService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		session.invalidate();
		return "index";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, HttpSession session,
			Model model) {

		//入力エラーがある場合
		if (result.hasErrors()) {
			return index(loginForm);
		}

		LoginResult loginResult = loginService.execute(loginForm);

		if (loginResult.isLogin()) {//ログイン成功時
			session.setAttribute("user", loginResult.getLoginUser());
			// 一覧へリダイレクト
			return "redirect:/list";

		} else {//ログイン失敗時
			model.addAttribute("errMessage", loginResult.getErrorMsg());
			return "index";
		}

	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout() {
		// セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

}
