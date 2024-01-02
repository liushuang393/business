package org.com.business.spring.boot.fw.core.auth.config;

import org.com.business.spring.boot.fw.core.auth.service.CostmUserDetailsService;
import org.com.business.spring.boot.fw.core.filter.CsrfCookieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@EnableGlobalAuthentication
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// @Secured("ROLE_USER")

	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CostmUserDetailsService userDetailsService;

	//////////////////////////////////////////////////////////////////////////
	// PROPERTIES //
	//////////////////////////////////////////////////////////////////////////

	@Value("${custom.security.rememberme-secret}")
	private String secret;
	@Value("${custom.security.rememberme-create-tables}")
	private String createTables;

	// フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	// @Bean
	// public AuthenticationProvider getAuthenticationProvider() {
	// return new CostmUserDetailsAuthenticationProvider();
	// }

	// @Bean
	// @Override
	// protected AuthenticationManager authenticationManager() throws Exception
	// {
	// return super.authenticationManager();
	// }

	// @Bean
	// public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	// StrictHttpFirewall firewall = new StrictHttpFirewall();
	// firewall.setAllowUrlEncodedSlash(true);
	// firewall.setAllowSemicolon(true);
	// return firewall;
	// }

	@Override
	public void configure(WebSecurity web) throws Exception {

		super.configure(web);
		// web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**",
				"/js/**", "/error**","/","/index.html");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// BASIC認証の有効化
		//http.httpBasic();
		// http.authorizeRequests().anyRequest().authenticated();
		//http.headers().frameOptions().disable();
		// 認可の設定
		http.authorizeRequests()
				.requestMatchers(EndpointRequest.toAnyEndpoint())
				.hasRole("ADMIN").anyRequest().authenticated()// ActuatorエンドポイントはACTUATORロールのみアクセス可能
				.antMatchers("/", "/error", "/welcome", "/commonError","/index.html",
						"/changeLocale", "/permit/**")
				.permitAll() // indexは全ユーザーアクセス許可
				.antMatchers("/system/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/sales/**").hasRole("BUSINESS").anyRequest()
				.authenticated(); // それ以外は全て認証無しの場合アクセス不許可

		// ログイン設定
		http.formLogin().loginPage("/welcome") // ログイン画面
				.loginProcessingUrl("/login") // 認証処理のパス
				.failureHandler(customAuthenticationFailureHandler) // 認証失敗時に呼ばれるハン
				.successHandler(customAuthenticationSuccessHandler)// 認証成功時に呼ばれるハン
				// .defaultSuccessUrl("/menu") // 認証成功時の遷移先
				.usernameParameter("userId").passwordParameter("password") // ユーザー名、パスワードのパラメータ名
				// .failureUrl("/index.html")
				.permitAll()// indexは全ユーザーアクセス許可
				.and()

				// ログアウト設定
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**")) // ログアウト処理のパス
				.logoutSuccessUrl("/welcome") // ログアウト完了時のパス
				.deleteCookies("JSESSIONID").and().headers().frameOptions()
				.disable().and()
				// Spring SecurityのFilter Chainに追加する。
				.addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class);
		// .rememberMe()
		// .rememberMeServices(rememberMeService())
		// .and()
		// .apply(new SpringSocialConfigurer())
		// .and

		// 本番ではCSRFは無効化しないで！
		http.csrf().disable();

	}

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// /**３種類選択できる。*/
	// //inMemoryAuthentication
	// //auth.inMemoryAuthentication().passwordEncoder(new
	// BCryptPasswordEncoder()).withUser("user1").password(new
	// BCryptPasswordEncoder().encode("123123")).roles("USER");
	//
	// //jdbcAuthenticationデータベースから取得、securityで提供されたテール。
	// //usersByUsernameQuery
	// //authoritiesByUsernameQuery
	// //auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query).authoritiesByUsernameQuery(query);
	//
	// //インジェクターuserDetailsService，userDetailsServiceインターフェース
	// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// 本番
		 //auth.authenticationProvider(getAuthenticationProvider());
		 auth.userDetailsService(userDetailsService)
		 .passwordEncoder(passwordEncoder());

//		auth.inMemoryAuthentication()
//				.passwordEncoder(NoOpPasswordEncoder.getInstance()) // 本番ではNoOpPasswordEncoder禁止！
//				.withUser("actuator").password("password").roles("ACTUATOR")
//				.and().withUser("user").password("password").roles("USER");
	}

	//////////////////////////////////////////////////////////////////////////
	// REMEMBER ME //
	//////////////////////////////////////////////////////////////////////////

	// @Bean
	// public JdbcTokenRepositoryImpl jdbcTokenRepository() {
	// JdbcTokenRepositoryImpl jdbcTokenRepository = new
	// JdbcTokenRepositoryImpl();
	// jdbcTokenRepository.setDataSource(dataSource);
	// jdbcTokenRepository.setCreateTableOnStartup(Boolean.valueOf(createTables));
	// return jdbcTokenRepository;
	// }
	//
	// @Bean
	// public RememberMeAuthenticationProvider
	// rememberMeAuthenticationProvider() {
	// return new RememberMeAuthenticationProvider(secret);
	// }
	//
	// @Bean
	// public PersistentTokenBasedRememberMeServices rememberMeService() {
	// PersistentTokenBasedRememberMeServices service =
	// new PersistentTokenBasedRememberMeServices(secret, userDetailsService(),
	// jdbcTokenRepository());
	// service.setUseSecureCookie(true);
	// service.setParameter("rememberme");
	// service.setTokenValiditySeconds(AbstractRememberMeServices.TWO_WEEKS_S);
	// return service;
	// }
	//
	// @Bean
	// public RememberMeAuthenticationFilter authenticationFilter() throws
	// Exception {
	// return new RememberMeAuthenticationFilter(authenticationManager(),
	// rememberMeService());
	// }

}