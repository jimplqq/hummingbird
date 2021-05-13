package controllers.jav.test;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.javadsl.Source;
import com.google.common.collect.ImmutableList;
import common.filters.LoggingFilter;
import common.util.BeanCopierUtil;
import io.ebean.Ebean;
import javaBeans.vo.request.TestRequest;
import javaBeans.vo.response.TestResponse;
import models.test.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.FormFactory;
import play.filters.csrf.CSRF;
import play.filters.csrf.RequireCSRFCheck;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.test.TestRepository;
import services.TestService;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * @author qingyun
 * @date 2021/5/8 3:54 下午
 */
public class EbeanTestController extends Controller {
  private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  @Inject private TestService testService;
  @Inject private TestRepository testRepository;
  @Inject private HttpExecutionContext httpExecutionContext;
  @Inject private FormFactory formFactory;

  @RequireCSRFCheck
  public CompletionStage<Result> save(Http.Request request) {
    String no_token = CSRF.getToken(request).map(CSRF.Token::value).orElse("no token");
    TestRequest testRequest = formFactory.form(TestRequest.class).bindFromRequest(request).get();
    return testRepository
        .save(BeanCopierUtil.copyProperties(testRequest, Test.class))
        .thenApplyAsync(id -> ok(Json.toJson(id)), httpExecutionContext.current());
  }

  public Result testList() {
    List<TestResponse> testResponses = testService.testList();
    logger.info("testResponses={}", Json.toJson(testResponses));
    return ok(Json.toJson(testResponses));
  }

  public CompletionStage<Result> test_list() {
    return testRepository
        .list()
        .thenApplyAsync(list -> ok(Json.toJson(list)), httpExecutionContext.current());
  }

  public CompletionStage<Result> batch_save() {
    return testRepository
        .batch_save(this.buildData())
        .thenApplyAsync(i -> ok(Json.toJson(i)), httpExecutionContext.current());
  }

  private List<Test> buildData() {
    final ActorSystem actorSystem = ActorSystem.create("QuickStart");
    Source<Integer, NotUsed> range = Source.range(0, 10);
    ImmutableList.Builder<Test> builder = ImmutableList.builder();
    CompletionStage<Done> doneCompletionStage =
        range.runForeach(
            i -> {
              Test test = new Test();
              test.setName(i + "");
              builder.add(test);
            },
            actorSystem);
    doneCompletionStage.toCompletableFuture().join();
    ImmutableList<Test> build = builder.build();
    System.out.println(build.size());
    return build;
  }

  public Result batchSave() {
    int i = Ebean.saveAll(this.buildData());
    return ok(String.valueOf(i));
  }
}
