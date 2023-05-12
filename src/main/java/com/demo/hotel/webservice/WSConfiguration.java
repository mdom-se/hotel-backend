package com.demo.hotel.webservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WSConfiguration extends WsConfigurerAdapter {

    static final String WS_TARGET_NAMESPACE = "http://demo.hotel.com/hotel-ws";
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/hotel-ws/*");
    }
    @Bean(name = "hotels")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema hotelsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HotelsPort");
        wsdl11Definition.setLocationUri("/hotel-ws");
        wsdl11Definition.setTargetNamespace(WS_TARGET_NAMESPACE);
        wsdl11Definition.setSchema(hotelsSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema hotelsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/hotel-ws.xsd"));
    }
}
