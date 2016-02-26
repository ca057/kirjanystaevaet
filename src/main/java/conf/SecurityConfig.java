package conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import appl.data.enums.UserRoles;

@Configuration
@ComponentScan(basePackages = { "appl.logic.security" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
	}

	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
		// auth.inMemoryAuthentication().withUser("user").password("user").roles(UserRoles.USER.toString());
		// TODO ggf configure(...) { ... }
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#authorize-requests
		http.authorizeRequests()
				.antMatchers("/", "/kategorien", "/kategorie/**", "/buch/**", "/suche", "/kontakt", "/login", "/logout",
						"/warenkorb", "/registrierung")
				.permitAll().antMatchers("/meinkonto", "/meinkonto/**").hasRole(UserRoles.USER.toString())
				.antMatchers("/backend", "/backend/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error").and().logout()
				.deleteCookies("remove").invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/?logout")
				.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
}
