package ts_ec_kadai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import ts_ec_kadai.handler.AuthenticationSuccessHandlerImpl;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers("/css/**","/webjars/**","/js/**","/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.authorizeRequests().antMatchers("/","/signin/**","/cart/**","/product/**","/signup/**").permitAll()
		.anyRequest().authenticated().and().formLogin().loginPage("/signin").usernameParameter("username")
		.passwordParameter("password").permitAll().and().logout().permitAll();
		
		http.formLogin().failureForwardUrl("/signin/fail").successHandler(authenticationSuccessHandlerImpl());
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.deleteCookies("JSESSIONID").invalidateHttpSession(true).permitAll();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureAuthenticationManager(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl() {
		return new AuthenticationSuccessHandlerImpl();
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
	
	


}
