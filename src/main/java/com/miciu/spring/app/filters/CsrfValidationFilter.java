package com.miciu.spring.app.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.miciu.spring.app.filters.CsrfGrantingFilter.CSRF_COOKIE_NAME;
import static com.miciu.spring.app.filters.CsrfGrantingFilter.CSRF_HEADER_NAME;

/**
 * 1rst task: Invalid CSRF Token: header missing
 */
public class CsrfValidationFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfValidationFilter.class);

    private static final Pattern WRITE_METHODS = Pattern.compile("^(POST|PUT|DELETE)$");
    private static final String INVALID_CSRF_TOKEN_MSG = "Invalid CSRF Token";

    private String cookiePath = "/";

    public CsrfValidationFilter setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
        return this;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        if (WRITE_METHODS.matcher(request.getMethod()).matches()) {
            //TODO implement logic here
            if (!isCsrfTokenValid(request)) {
                removeCsrfCookie(response);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().println(INVALID_CSRF_TOKEN_MSG);
            }
        } else {
            filterChain.doFilter(request, response);

        }
    }

    private boolean isCsrfTokenValid(HttpServletRequest request) {
        String header = request.getHeader(CSRF_HEADER_NAME);
        String cookie = Optional.ofNullable(WebUtils.getCookie(request, CSRF_COOKIE_NAME)).map(Cookie::getValue).orElse(null);

        if (header == null) {
            LOGGER.warn("Invalid CSRF Token: header missing");
            return false;
        }

        if (cookie == null) {
            LOGGER.warn("Invalid CSRF Token: cookie missing");
            return false;
        }

        if (!header.equals(cookie)) {
            LOGGER.warn("Invalid CSRF Token: header '{}' and cookie '{}' did not match", header, cookie);
            return false;
        }

        return true;
    }

    /**
     * To remove a cookie you need to create new one
     *
     * @param response
     */
    private void removeCsrfCookie(HttpServletResponse response) {
        final Cookie cookie = createCsrfCookie(null);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        response.addCookie(cookie);
    }

    private Cookie createCsrfCookie(String value) {
        Cookie cookie = new Cookie(CSRF_COOKIE_NAME, value);
        cookie.setPath(cookiePath);
        return cookie;
    }
}
