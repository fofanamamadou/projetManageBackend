/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispatecgestapprov.demo.config;

/**
 *
 * @author PIERRE
 */
import java.io.IOException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
        
        response.setHeader("Access-Control-Allow-Origin", "*");
    // response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
    //     response.setHeader("Access-Control-Allow-Origin", "http://10.10.3.143:8080/mobile");
   
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store"); 
response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
response.setHeader("Access-Control-Max-Age", "3600000");
response.setHeader("Access-Control-Allow-Credentials", "true");
response.setHeader("Access-Control-Allow-Headers",
	"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
response.setStatus(HttpServletResponse.SC_OK);
} else {
chain.doFilter(req, resp);
}
		
	}


}
