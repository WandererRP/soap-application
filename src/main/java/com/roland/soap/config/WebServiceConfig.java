package com.roland.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author Roland Pilpani 28.12.2022
 */

@Configuration
@EnableWs
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
       return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
    }

    //http://soap.roland.com/dto/accounts

    // ws/accounts
    @Bean(name = "accounts")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema accountSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("AccountPort");
        definition.setTargetNamespace("http://soap.roland.com/dto/accounts");
        definition.setLocationUri("/ws");
        definition.setSchema(accountSchema);

        return definition;
    }



    @Bean
    public XsdSchema accountSchema(){
        return new SimpleXsdSchema(new ClassPathResource("account-details.xsd"));
    }

}
