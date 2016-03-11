package conf;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.MultipartFile;

/**
 * Primarily needed to manage file upload via {@link MultipartFile} in
 * conjunction with prevention of {@code CSRF}. Adds an additional filter to
 * process these files .
 * 
 * @author Christian
 * @author Johannes
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

//	@Override
//	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//		super.beforeSpringSecurityFilterChain(servletContext);
//
//		FilterRegistration.Dynamic springMultipartFilter;
//		springMultipartFilter = servletContext.addFilter("springMultipartFilter", new MultipartFilter());
//		springMultipartFilter.addMappingForUrlPatterns(null, false, "/*");
//
//	}

}
