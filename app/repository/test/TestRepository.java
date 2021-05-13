package repository.test;

import common.util.BeanCopierUtil;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import javaBeans.dto.TestDto;
import javaBeans.vo.response.TestResponse;
import models.test.Test;
import play.db.ebean.EbeanConfig;
import repository.DatabaseExecutionContext;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author qingyun
 * @date 2021/5/11 3:17 下午
 */
public class TestRepository {
  private final EbeanServer ebeanServer;

  private final DatabaseExecutionContext context;

  @Inject
  public TestRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext context) {
    this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
    this.context = context;
  }

  public CompletionStage<List<TestResponse>> list() {
    return CompletableFuture.supplyAsync(
        () ->
            BeanCopierUtil.copyObjects(
                ebeanServer.findDto(TestDto.class, "select id,name from test").findList(),
                TestResponse.class),
        context);
  }

  public CompletionStage<Integer> batch_save(List<Test> tests) {
    return CompletableFuture.supplyAsync(() -> ebeanServer.saveAll(tests), context);
  }

  public CompletionStage<Long> save(Test test) {

    return CompletableFuture.supplyAsync(
        () -> {
          test.save();
          return test.getId();
        },
        context);
  }
}
