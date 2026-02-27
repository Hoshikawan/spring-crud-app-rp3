package jp.co.sss.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringCrudAnswerApplication extends SpringBootServletInitializer {//warにデプロイするにはSpringBootServletInitializerを継承する

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudAnswerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO 自動生成されたメソッド・スタブ
		return builder.sources(SpringCrudAnswerApplication.class);
	}

}
