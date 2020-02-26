package com.kunyk.cinemaapi.configuration;

import com.kunyk.cinemaapi.model.User;
import com.kunyk.cinemaapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

    private final UserService userService;

    @Autowired
    public AuthorizationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String email = request.getHeader(HttpHeaders.FROM);

        User user = userService.getByEmail(email);
        request.setAttribute(User.CURRENT_USER, user);

        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
