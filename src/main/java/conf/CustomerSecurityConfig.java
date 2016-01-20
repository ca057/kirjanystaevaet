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
@Order(1)
@EnableWebSecurity
public class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public CustomerSecurityConfig() {
	}

	public CustomerSecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/kategorie/**", "/kategorien", "/suche", "/kontakt", "/login", "/logout").permitAll()
				.antMatchers("/meinkonto").hasRole("USER").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/meinkonto").failureUrl("/login?error").and().logout()
				.deleteCookies("remove").invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/?logout")
				.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
}
