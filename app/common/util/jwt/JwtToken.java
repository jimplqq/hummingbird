package common.util.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author qingyun
 * @date 2021/5/12 4:58 下午
 */
public class JwtToken {
  /**
   * @param key 不能太短
   * @param value
   * @return
   */
  public String getToken(String key, String value) {
    JwtBuilder jwtBuilder =
        Jwts.builder()
            .setId(key)
            .setSubject(value)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, value);
    return jwtBuilder.compact();
  }
}
