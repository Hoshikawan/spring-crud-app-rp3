package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ログインチェック用フィルタ
 * 
 * @author System Shared
 */
public class LoginCheckFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// セッション情報を取得
		HttpSession session = request.getSession();

		// 不正アクセスの場合、ログイン画面にリダイレクト
		if (session.getAttribute("user") == null) {
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
