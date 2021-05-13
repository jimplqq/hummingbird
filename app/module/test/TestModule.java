package module.test;

import com.google.inject.AbstractModule;
import services.TestService;
import services.impl.TestServiceImpl;

/**
 * @author qingyun
 * @date 2021/5/12 3:27 下午
 */
public class TestModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(TestService.class).to(TestServiceImpl.class);
  }
}
