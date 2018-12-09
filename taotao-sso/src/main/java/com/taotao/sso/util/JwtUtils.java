package com.taotao.sso.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-09-28
 * Time: 22:53
 */
public final class JwtUtils {
    static {
        generatorKeyPair();
    }

    private static final String JWT_SECRET_KEY = "3d990d2276917dfac04467df11fff26d";
    private static final long MILLIS = 5 * 60 * 1000;
    private static KeyPair keyPair;

    private static void generatorKeyPair() {
        KeyPairGenerator pairGenerator = null;
        try {
            pairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        pairGenerator.initialize(2048);
        keyPair = pairGenerator.genKeyPair();
    }

    public static String createJwt(Map<String, Object> claimsMap, long millis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //第一种方法
        /*
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setIssuer(issuer).setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(signatureAlgorithm, JWT_SECERT);*/

        //第二种方法
        /*
        PrivateKey privateKey = keyPair.getPrivate();
        JwtBuilder jwtBuilder = Jwts.builder().setId("jwt").setExpiration(new Date(System.currentTimeMillis() + (5 * 60 * 1000))).
                signWith(SignatureAlgorithm.RS512, privateKey);*/

        //第三种方法
        SecretKey secretKey = generalKey();
        if (millis <= 0) {
            millis = MILLIS;
        }
        Date date = new Date(System.currentTimeMillis() + millis);
        System.out.println(date);
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claimsMap).signWith(signatureAlgorithm, secretKey)
                .setExpiration(date);
        return jwtBuilder.compact();
    }

    public static Claims parseJwt(String token) throws Exception {
        //第一种方法
        //Claims claims = Jwts.parser().setSigningKey(JWT_SECERT).parseClaimsJws(token).getBody();
        //第二种方法
        /*
        PublicKey publicKey = keyPair.getPublic();
        JwtParser jwtParser = Jwts.parser();
        Claims claims = jwtParser.setSigningKey(publicKey).parseClaimsJws(token).getBody();
       */
        //第三种方法
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
            Claims claims = jws.getBody();
            return claims;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
            String message;
            //
            if (e instanceof ExpiredJwtException) {
                message = "Token已过期，请获取新Token";
            } else {
                message = "无效的Token";
            }
            throw new Exception(message, e);
        }
    }

    private static SecretKey generalKey() {
        byte[] bytes = JWT_SECRET_KEY.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(bytes, "AES");
        return keySpec;
    }
}
