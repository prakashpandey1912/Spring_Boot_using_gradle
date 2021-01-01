package com.tm.one;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.servlet.support.*;
import org.springframework.boot.builder.*;
@SpringBootApplication
public class OneApplication extends SpringBootServletInitializer
{
public static void main(String[] args) {
SpringApplication.run(OneApplication.class, args);
}
protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
{
return application.sources(OneApplication.class);
}
}
