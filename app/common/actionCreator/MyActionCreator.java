package common.actionCreator;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * @author qingyun
 * @date 2020/11/27 3:33 下午
 */
public class MyActionCreator implements play.http.ActionCreator {
  private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyActionCreator.class);

  @Override
  public Action createAction(Http.Request request, Method actionMethod) {
    log.info(
        "请求request = {}",
        Optional.ofNullable(request)
            .map(Http.Request::body)
            .map(Http.RequestBody::asJson)
            .map(JsonNode::toString)
            .orElse(""));
    return new Action.Simple() {
      @Override
      public CompletionStage<Result> call(Http.Request req) {
        return delegate.call(req);
      }
    };
  }
}
