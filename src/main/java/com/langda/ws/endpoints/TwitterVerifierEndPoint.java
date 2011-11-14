package com.langda.ws.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.langda.ws.service.TwitterVerifierService;

@WebService(serviceName="TwitterVerifierService")
public class TwitterVerifierEndPoint extends SpringBeanAutowiringSupport{

	@Autowired
	 private TwitterVerifierService helloWorldService;
	 
	 @WebMethod
	 public String sayHello() {
	  return helloWorldService.sayHello();
	 }
	 
}
