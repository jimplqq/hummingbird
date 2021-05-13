package services.impl;

import akka.actor.ActorSystem;
import common.util.BeanCopierUtil;
import common.util.ebean.EbeanUtil;
import javaBeans.dto.TestDto;
import javaBeans.vo.response.TestResponse;
import services.TestService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author qingyun
 * @date 2021/5/12 3:26 下午
 */
public class TestServiceImpl implements TestService {
  private final ActorSystem actorSystem;

  @Inject
  public TestServiceImpl(ActorSystem actorSystem) {
    this.actorSystem = actorSystem;
  }

  @Override
  public List<TestResponse> testList() {
    actorSystem.log().info("aaa");
    return BeanCopierUtil.copyObjects(
        EbeanUtil.queryDto(TestDto.class, "select id,name from test").findList(),
        TestResponse.class);
  }
}
