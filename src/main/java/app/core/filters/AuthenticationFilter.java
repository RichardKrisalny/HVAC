package app.core.filters;


import app.core.jwt.JwtUser;
import app.core.jwt.User;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class AuthenticationFilter implements Filter {

	private JwtUser jwtUser;

	public AuthenticationFilter(JwtUser jwtUser) {
		super();
		this.jwtUser = jwtUser;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestMethod = httpRequest.getMethod();
		if (requestMethod.equalsIgnoreCase("options")) {
			chain.doFilter(request, response);
		} else {
			try {
				String authorization = httpRequest.getHeader("Authorization");
				StringTokenizer tokenizer = new StringTokenizer(authorization);
				String scheme = tokenizer.nextToken();
				String token = tokenizer.nextToken();
				User user = this.jwtUser.extractUser(token);
				httpRequest.setAttribute("user", user);
				chain.doFilter(request, response);
			} catch (Exception e) {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.setHeader("Access-Control-Allow-Origin", "*");
				resp.setHeader("WWW-Authenticate", "Bearer realm=\"General API\"");
				resp.setHeader("Access-Control-Expose-Headers", "*");
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You need to login - " + e.getMessage());
			}
		}
	}
}
