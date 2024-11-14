package com.oyameen.SpringBootSecurity3.constants;

import java.util.Date;

public class JwtProperties {

    public static final Date ISSUED_TIME = new Date(System.currentTimeMillis());
    public static final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + 1000 * 60 * 1); // 10 min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String KEY_GENERATOR_ALGO = "HmacSHA256";
}
