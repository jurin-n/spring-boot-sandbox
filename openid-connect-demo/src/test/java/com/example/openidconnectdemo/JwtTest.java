package com.example.openidconnectdemo;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.security.Key;

public class JwtTest {
	@Test
	public void testCompressAndParse() {
		Key key = MacProvider.generateKey();
		String compactJws = Jwts.builder().setSubject("Joe").compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println("**compactJws**");
		System.out.println(compactJws);

		System.out.println("**Jwts.parser**");
		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws));
		assertThat(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject(), is("Joe"));
	}
}
