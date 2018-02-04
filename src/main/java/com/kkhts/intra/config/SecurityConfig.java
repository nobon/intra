package com.kkhts.intra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kkhts.intra.services.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/css/**", "/img/**", "/js/**"); // トップページで読み込むリソースファイルへのアクセスを許可
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 認証なしでアクセスできるページの宣言
		http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated();

		// login処理をメソッドチェーンで記述
		http.csrf().disable().formLogin().loginPage("/login?timeout") // ログイン開始画面
				.loginProcessingUrl("/login") // 認証アクション時のアクセス先
				.failureUrl("/login?error") // 失敗時にどこに遷移するか
				.defaultSuccessUrl("/event/", true) // ログイン成功時の遷移先
				.usernameParameter("loginId") // ユーザー名として飛んでくるパラメータ名
				.passwordParameter("password");// パスワードとして飛んでくるパラメータ名

		// logout時の処理を宣言
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**")).logoutSuccessUrl("/login?logout");
	}

	@Configuration
	static class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private LoginUserDetailsService userDetailsService;

		@Bean // パスワードの暗号化方式を宣言（平文でDBにパスワードを保存しないこと！）
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}

	}

}