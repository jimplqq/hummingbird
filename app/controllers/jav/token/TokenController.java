package controllers.jav.token;

import common.util.jwt.JwtToken;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * @author qingyun
 * @date 2021/5/12 5:01 下午
 */
public class TokenController extends Controller {

  private JwtToken jwtToken;

  @Inject
  public TokenController(JwtToken jwtToken) {
    this.jwtToken = jwtToken;
  }

  public Result token() {
    return ok(jwtToken.getToken("1111", "2111"));
  }
}
