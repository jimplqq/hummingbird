package controllers.jav;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

  public Result index() {
    return ok("play2.8.8");
  }
}
