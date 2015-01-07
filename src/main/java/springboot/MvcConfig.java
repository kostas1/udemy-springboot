package springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addWebRequestInterceptor(new WebRequestInterceptor() {


			@Override
			public void preHandle(WebRequest request) throws Exception {
			}

			@Override
			public void postHandle(WebRequest request, ModelMap modelMap)
					throws Exception {
				if (modelMap != null) {
	                    modelMap.put("authentication", SecurityContextHolder.getContext().getAuthentication());
	                }
			}

			@Override
			public void afterCompletion(WebRequest request, Exception ex)
					throws Exception {
			}
        });
    }
}