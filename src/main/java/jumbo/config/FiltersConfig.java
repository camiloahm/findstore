package jumbo.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class setups all the HTTP filters inside the app
 */
@Configuration
class FiltersConfig {

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {

        final FilterRegistrationBean registration = new FilterRegistrationBean();
        final CorsFilter corsFilter = new CorsFilter();
        registration.setFilter(corsFilter);
        registration.addUrlPatterns("*");
        registration.setName(CorsFilter.class.getSimpleName());
        registration.setOrder(1);
        return registration;
    }

}
