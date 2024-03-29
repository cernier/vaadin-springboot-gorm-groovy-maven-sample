package com.example.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter
import org.vaadin.artur.helpers.LaunchUtil

import com.example.application.init.BootStrap
import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = 'myapp')
@PWA(name = 'My App', shortName = 'My App', offlineResources = [ 'images/logo.png' ])
public class Application extends SpringBootServletInitializer implements AppShellConfigurator, ApplicationRunner {

    @Autowired
    BootStrap bootStrap

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args))
    }

    @Bean
    public FilterRegistrationBean<OpenSessionInViewFilter> registerOpenSessionInViewFilterBean() {
        FilterRegistrationBean<OpenSessionInViewFilter> registrationBean = new FilterRegistrationBean<>()
        OpenSessionInViewFilter filter = new OpenSessionInViewFilter()
        registrationBean.setFilter(filter)
        return registrationBean
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        bootStrap.init()
    }
}
