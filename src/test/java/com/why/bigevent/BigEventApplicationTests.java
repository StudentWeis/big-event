package com.why.bigevent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

@SpringBootTest
class BigEventApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void jwtCreate() {
		System.out.println("JWT Create");
		Map<String, Object> claim = new HashMap<>();
		claim.put("name", "John");
		claim.put("age", 30);

		String token = JWT.create()
				.withClaim("user", claim)
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.sign(Algorithm.HMAC256("secret"));

		System.err.println(token);
	}

	// @Test
	// void jwtVerify() {
	// 	// 如果数据发生了篡改，则会失败
	// 	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7Im5hbWUiOiJKb2huIiwiYWdlIjozMH0sImV4cCI6MTcxOTUwMzk2OX0.Hqzt0A7D4brQgPPgX1HJucRiBujw27AFCkwr4cfmzws";
	// 	JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("secret")).build();
	// 	Map<String, Claim> claims = jwtVerifier.verify(token).getClaims();
	// 	System.out.println(claims);
	// }
}
