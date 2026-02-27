package jp.co.sss.crud.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.sss.crud.filter.AdminAccountCheckFilter;
import jp.co.sss.crud.filter.LoginCheckFilter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

	@Bean
	FilterRegistrationBean<LoginCheckFilter> configLoginCheckFilter() {
		FilterRegistrationBean<LoginCheckFilter> bean = new FilterRegistrationBean<LoginCheckFilter>();

		bean.setFilter(new LoginCheckFilter());
		// ログインチェックが必要なURLパターンを設定
		bean.addUrlPatterns("/list", "/regist/*", "/update/*", "/delete/*");
		bean.setOrder(1);
		return bean;
	}

	@Bean
	FilterRegistrationBean<AdminAccountCheckFilter> configAdminAccountCheckFilter() {
		FilterRegistrationBean<AdminAccountCheckFilter> bean = new FilterRegistrationBean<AdminAccountCheckFilter>();

		bean.setFilter(new AdminAccountCheckFilter());
		// 管理者権限が必要なURLパターンを設定
		bean.addUrlPatterns("/regist/*", "/update/*", "/delete/*");
		bean.setOrder(2);
		return bean;
	}

}