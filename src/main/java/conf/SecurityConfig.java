package conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("chabo")
				.password("password").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// for public requests and customers
		http.authorizeRequests()
				.antMatchers("/", "/kategorie/**", "/kategorien", "/suche", "/kontakt", "/login", "/logout").permitAll()
				.antMatchers("/meinkonto").hasRole("USER").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/meinkonto").failureUrl("/login?error").and().logout()
				.deleteCookies("remove").invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/?logout")
				.permitAll();

		// FIXME this does not work properly: If I configure it this way, I can
		// use the normal customer login form to log into the admin area, but
		// this should not be the case. they should be separated
		// for admin access
		http.authorizeRequests().antMatchers("/backend/login", "/backend/logout").permitAll()
				.antMatchers("/backend", "/backend/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
				.loginPage("/backend/login").defaultSuccessUrl("/backend").failureUrl("/backend/login?error")
				.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
}
