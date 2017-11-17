package com.Spring.App;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.web.server.ErrorPage;
import org.springframework.http.HttpStatus;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

//Spring boot 2.0 
/*@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  @Override
  public void customize(ConfigurableServletWebServerFactory server) {
      //重新设置程序端口，与properties文件配置相同
	  //server.setPort(9000);
      
	  //重新定义error page，进行跳转
      ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
      ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
      ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
      server.addErrorPages(error401Page,error404Page,error500Page);
  }

}*/

//Spring boot 2.0版本以下
@Component
public class CustomizationBean 
implements EmbeddedServletContainerCustomizer {

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
      //container.setPort(8083);
  }

/**
 * 自定义异常页 spring 2.0 不能实现下面方法
 */
 @Bean
public EmbeddedServletContainerCustomizer containerCustomizer() {

   return (container -> {
        ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        container.addErrorPages(error401Page, error404Page, error500Page);
   });
 }
}