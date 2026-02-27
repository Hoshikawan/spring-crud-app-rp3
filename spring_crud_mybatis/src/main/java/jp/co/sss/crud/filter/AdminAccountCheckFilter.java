package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sss.crud.entity.Employee;

/**
 * ログインチェック用フィルタ
 * 
 * @author System Shared
 */
public class AdminAccountCheckFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();

		//完了画面はフィルターを通過させる
		if (requestURI.contains("/complete") && requestMethod.equals("GET")) {
			chain.doFilter(request, response);
			return;
		}

		// セッション情報を取得
		HttpSession session = request.getSession();
		Employee sessionUser = ((Employee) session.getAttribute("user"));
		Integer sessionUserAuthority = -1;
		Integer sessionUserEmpId = -1;

		if (sessionUser != null) {
			sessionUserAuthority = sessionUser.getAuthority();
			sessionUserEmpId = sessionUser.getEmpId();
		}

		// 更新対象の社員IDを取得
		String param = request.getParameter("empId");
		Integer requestEmpId = null;
		if (param != null) {
			requestEmpId = Integer.parseInt(param);
		}

		boolean accessFlg = false;

		if (sessionUserAuthority == 2) {
			// 管理者の場合、アクセス許可
			accessFlg = true;
		} else if (sessionUserEmpId == requestEmpId) {
			// ログインユーザ自身の画面はアクセス許可
			accessFlg = true;
		}

		if (!accessFlg) {
			// レスポンス情報を取得
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			// ログイン画面へリダイレクト
			httpResponse.sendRedirect(request.getContextPath());
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}

	}

}
