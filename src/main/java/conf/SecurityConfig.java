package conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
	}

	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#authorize-requests
		http.authorizeRequests()
				.antMatchers("/", "/kategorie/**", "/kategorien", "/suche", "/kontakt", "/login", "/logout").permitAll()
				.antMatchers("/meinkonto*").hasRole("USER").antMatchers("/backend*").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
				.failureUrl("/login?error");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
}
