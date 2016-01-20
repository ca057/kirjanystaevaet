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
		// for public requests and customers
		http.authorizeRequests()
				.antMatchers("/", "/kategorie/**", "/kategorien", "/suche", "/kontakt", "/login", "/logout").permitAll()
				.antMatchers("/meinkonto").hasRole("USER").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/meinkonto").failureUrl("/login?error").and().logout()
				.deleteCookies("remove").invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/?logout")
				.permitAll();

		// FIXME this does not work properly: If I configure it this way, I can
		// use the normal customer login form to log into the admin area, but
		// this should not be the case. it should be a separate one
		// for the admin access
//		http.authorizeRequests().antMatchers("/backend/login", "/backend/logout").permitAll()
//				.antMatchers("/backend", "/backend/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin()
//				.loginPage("/backend/login").defaultSuccessUrl("/backend").failureUrl("/backend/login?error").and()
//				.logout().deleteCookies("remove").invalidateHttpSession(true).logoutUrl("/logout")
//				.logoutSuccessUrl("/?logout-admin").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
}
