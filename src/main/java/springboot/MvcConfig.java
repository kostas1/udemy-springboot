package springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springboot.helpers.TemplateMessageHolder;

import java.util.Locale;

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
		registry.addInterceptor(localeChangeInterceptor());
        registry.addWebRequestInterceptor(new WebRequestInterceptor() {


			@Override
			public void preHandle(WebRequest request) throws Exception {
			}

			@Override
			public void postHandle(WebRequest request, ModelMap modelMap)
					throws Exception {
				if (modelMap != null) {
					modelMap.put("authentication", SecurityContextHolder.getContext().getAuthentication());

					modelMap.put("messages", messageHolder());

					CsrfToken token = (CsrfToken) request.getAttribute("_csrf", RequestAttributes.SCOPE_REQUEST);
					if (token != null)
						modelMap.put("_csrf", token);
	                }
			}

			@Override
			public void afterCompletion(WebRequest request, Exception ex)
					throws Exception {
			}
        });
    }

	/**
	 * Should be named as localeResolver and nothing else.
	 * Otherwise Spring will use instance of AcceptHeaderLocaleResolver which we want to override
	 * @return
	 */
	@Bean
	SessionLocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Bean
	TemplateMessageHolder messageHolder() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages.messages");
		TemplateMessageHolder holder = new TemplateMessageHolder(source);
		return holder;
	}

}