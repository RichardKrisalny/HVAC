package app.core.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public abstract class JwtUtilAbstract<T, ID> {
	private String alg = SignatureAlgorithm.HS256.getJcaName();
	@Value("${jwt.util.secret}")
	private String secret;
	private Key key;
	@Value("${jwt.util.chrono.unit}")
	private String chronoUnit; // time units
	@Value("${jwt.util.chrono.unit.number}")
	private long unitNumber; // number of time units
	private JwtParser jwtParser;
	@PostConstruct
	void init() {
		this.key = new SecretKeySpec(Base64.getDecoder().decode(secret), alg);
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
	}
	public abstract String generateToken(T user) throws JsonProcessingException;
	protected String createToken(Map<String, Object> claims, ID id) {
		Instant now = Instant.now();
		System.out.println(now);
		Instant exp = now.plus(unitNumber, ChronoUnit.valueOf(chronoUnit));
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(id.toString())
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(exp))
				.signWith(key)
				.compact();
		return token;
	}

	public abstract T extractUser(String token) throws JwtException, JsonProcessingException;

	protected Claims extractAllClaims(String token) throws JwtException {
		Jws<Claims> jwt = this.jwtParser.parseClaimsJws(token);
		return jwt.getBody();
	}

}
