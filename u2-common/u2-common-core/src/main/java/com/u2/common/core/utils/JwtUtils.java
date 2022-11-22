package com.u2.common.core.utils;

import com.u2.common.core.constant.SecurityConstants;
import com.u2.common.core.constant.TokenConstants;
import com.u2.common.core.text.Convert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

/**
 * Jwt工具类
 *
 * @author vhans
 */
public class JwtUtils {
    public static String secret = TokenConstants.SECRET;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 根据令牌获取相应人员标识
     *
     * @param token 令牌
     * @param key   人员键
     * @return 人员ID
     */
    public static String getPeopleKey(String token, String key) {
        Claims claims = parseToken(token);
        return getValue(claims, key);
    }

    /**
     * 根据身份信息获取相应人员标识
     *
     * @param claims 身份信息
     * @param key    人员键
     * @return 人员ID
     */
    public static String getPeopleKey(Claims claims, String key) {
        return getValue(claims, key);
    }

    /**
     * 根据令牌获取相应人员ID
     *
     * @param token 令牌
     * @param key   人员键
     * @return 人员ID
     */
    public static String getPeopleId(String token, String key) {
        Claims claims = parseToken(token);
        return getValue(claims, key);
    }

    /**
     * 根据身份信息获取相应人员ID
     *
     * @param claims 身份信息
     * @param key   人员键
     * @return 人员ID
     */
    public static String getPeopleId(Claims claims, String key) {
        return getValue(claims, key);
    }

    /**
     * 根据令牌获取相应人员名
     *
     * @param token 令牌
     * @param key   人员键
     * @return 相应人员名
     */
    public static String getPeopleName(String token, String key) {
        Claims claims = parseToken(token);
        return getValue(claims, key);
    }

    /**
     * 根据身份信息获取相应人员名
     *
     * @param claims 身份信息
     * @param key   人员键
     * @return 相应人员名
     */
    public static String getPeopleName(Claims claims, String key) {
        return getValue(claims, key);
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key    键
     * @return 值
     */
    public static String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }
}
