package com.works.configs;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.models.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final RestTemplate restTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String urlService = "https://ipapi.co/json";
        InfoService infoService = restTemplate.getForObject(urlService, InfoService.class );
        System.out.println(infoService.getIp());

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        String agent = req.getHeader("user-agent");
        System.out.println( ipAddress );
        System.out.println( agent );

        String url = req.getRequestURI();
        String freeUrl = "/customer/login";
        boolean loginStatus = true;
        if ( url.equals(freeUrl) ) {
            loginStatus = false;
        }

        if (loginStatus) {
            boolean sessionStatus = req.getSession().getAttribute("customer") == null;
            if ( sessionStatus ) {
                String failData = "{ \"status\": false, \"message\": \"Please Login\" }";
                res.setStatus(401);
                res.setContentType("application/json");
                res.getWriter().println(failData);
            }else {
                Customer c = (Customer) req.getSession().getAttribute("customer");
                boolean serviceStatus = false;
                for ( Role item : c.getRoles() ) {
                    if ( url.contains(item.getName()) ) {
                        serviceStatus = true;
                    }
                }

                if ( serviceStatus ) {
                    filterChain.doFilter(req, res);
                }else {
                    String failData = "{ \"status\": false, \"message\": \"Forbidden\" }";
                    res.setStatus(403);
                    res.setContentType("application/json");
                    res.getWriter().println(failData);
                }

            }
        }else {
            filterChain.doFilter(req, res);
        }


    }

}
