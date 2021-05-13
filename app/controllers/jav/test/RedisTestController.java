package controllers.jav.test;

import play.api.cache.redis.CacheAsyncApi;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * @author qingyun
 * @date 2021/5/12 11:54 上午
 */
public class RedisTestController extends Controller {
  private final CacheAsyncApi cacheAsyncApi;

  @Inject
  public RedisTestController(CacheAsyncApi cacheAsyncApi) {
    this.cacheAsyncApi = cacheAsyncApi;
  }

  public Result setKey() {
    cacheAsyncApi.set("kkk", "aaa", Duration.create(10, TimeUnit.DAYS));
    return ok();
  }
}
