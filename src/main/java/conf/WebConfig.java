package conf;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan("web")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		converter.setObjectMapper(objectMapper);
		converters.add(converter);
		super.configureMessageConverters(converters);
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

	@Bean
	public MultipartResolver filterMultipartResolver() {
		CommonsMultipartResolver res = new CommonsMultipartResolver();
		res.setMaxUploadSize((long) (5 * Math.pow(2, 20)));
		return res;
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
