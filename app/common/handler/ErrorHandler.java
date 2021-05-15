package common.handler;

import com.typesafe.config.Config;
import common.myException.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/** Created by questiny on 17-2-23. */
public class ErrorHandler extends DefaultHttpErrorHandler implements HttpErrorHandler {
  private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

  @Inject
  public ErrorHandler(
      Config config,
      Environment environment,
      OptionalSourceMapper sourceMapper,
      Provider<Router> routes) {
    super(config, environment, sourceMapper, routes);
  }

  @Override
  protected CompletionStage<Result> onProdServerError(
      Http.RequestHeader request, UsefulException exception) {

    return CompletableFuture.completedFuture(
        Results.internalServerError(
            Json.toJson(ResultModel.resultModelFalse(exception.getMessage(), -1))));
  }

  @Override
  protected CompletionStage<Result> onForbidden(Http.RequestHeader request, String message) {
    return CompletableFuture.completedFuture(
        Results.forbidden("You're not allowed to access this resource."));
  }

  @Override
  public CompletionStage<Result> onClientError(
      Http.RequestHeader request, int statusCode, String message) {
    return CompletableFuture.completedFuture(
        Results.status(statusCode, "A client error occurred: " + message));
  }

  @Override
  public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
    String code = "ec" + System.currentTimeMillis();
    CompletableFuture.runAsync(() -> this.logMsg(exception, code));
    return CompletableFuture.completedFuture(
        Results.internalServerError(
            Json.toJson(ResultModel.resultModelFalse(exception.getMessage() + "-" + code, -1))));
  }

  public void logMsg(Throwable exception, String code) {
    logger.error("code:{}", code);
    exception.printStackTrace();
  }
}
