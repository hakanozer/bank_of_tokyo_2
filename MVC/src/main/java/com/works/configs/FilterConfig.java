package com.works.configs;

import com.works.entities.Admin;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String[] freeUrls = {"/", "/login"};
        boolean loginStatus = true;
        for( String item : freeUrls ) {
            if ( item.equals(url) ) {
                loginStatus = false;
            }
        }

        if ( loginStatus ) {
            // session control
            boolean control = req.getSession().getAttribute("admin") == null;
            if ( control ) {
                res.sendRedirect("/");
            }else {
                // Session Valid
                Admin admin = (Admin) req.getSession().getAttribute("admin");
                req.setAttribute("admin", admin);
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }

    }

}
