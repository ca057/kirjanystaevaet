package conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "/WEB-INF/layout/tiles.xml" });
		tiles.setCheckRefresh(true);
		return tiles;
	}

	@Bean
	public ViewResolver resolver() {
		return new TilesViewResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (registry != null) {
			// TODO caching aktivieren
			// registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/").setCachePeriod(3600);
			// registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/").setCachePeriod(3600);
			registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
			registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
			registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
		}
	}
}
