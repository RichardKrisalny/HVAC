package app.core.filters;


import app.core.entities.UserType;
import app.core.jwt.User;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getMethod().equalsIgnoreCase("options")) {
            chain.doFilter(request, response);
        } else {
            String requestUri = httpRequest.getRequestURI();
            User user = (User) httpRequest.getAttribute("user");
            if (requestUri.contains("/api/admin") && user.getUserType() != UserType.ADMIN) {
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"ADMIN API\"");
                httpResponse.setHeader("Access-Control-Expose-Headers", "*");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only Admin can access this zone!");
            } else if (requestUri.contains("/api/company") && user.getUserType() != UserType.COMPANY) {
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"COMPANY API\"");
                httpResponse.setHeader("Access-Control-Expose-Headers", "*");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only Company can access this zone!");
            } else if (requestUri.contains("/api/employee") && user.getUserType() != UserType.EMPLOYEE) {
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"EMPLOYEE API\"");
                httpResponse.setHeader("Access-Control-Expose-Headers", "*");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only Employee can access this zone!");
            } else if (requestUri.contains("/api/manager") && user.getUserType() != UserType.MANAGER) {
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"MANAGER API\"");
                httpResponse.setHeader("Access-Control-Expose-Headers", "*");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only Manager can access this zone!");
            } else if (requestUri.contains("/api/customer") && user.getUserType() != UserType.CUSTOMER) {
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpResponse.setHeader("WWW-Authenticate", "Bearer realm=\"CUSTOMER API\"");
                httpResponse.setHeader("Access-Control-Expose-Headers", "*");
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Only Customer can access this zone!");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}
