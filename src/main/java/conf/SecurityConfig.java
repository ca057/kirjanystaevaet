package conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
		// TODO Auto-generated constructor stub
	}

	public SecurityConfig(boolean disableDefaults) {
		super(disableDefaults);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Zugriff erlauben auf Homepage ("/"), alle Kategorien
		// ("/kategorie/..."), Suche ("/suche"), Kontakt/Impressum ("/kontakt")
		http.authorizeRequests().antMatchers("/", "/kategorie/**", "/suche", "/kontakt").permitAll();
		// Zugriff auf alle anderen Seiten beschränken
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.failureUrl("/login?error").permitAll();
		// FIXME siehe link unten für bessere Konfiguration, z.B. eigener
		// Logout-Handler und cookies löschen und sowas
		// http.logout().logoutUrl("/logout").logoutSuccessUrl("/?logout").invalidateHttpSession(true);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// allow access to all requests which start with css, js or img
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

	// TODO mehr Infos hier:
	// http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#abstractsecuritywebapplicationinitializer-with-spring-mvc
}
