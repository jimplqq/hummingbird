package common;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import common.nacos.init.nacos.Nacaos;
import play.Application;
import play.Logger;

/** Created by questiny on 17-4-17. */
@Singleton
public class OnStartUp {

  private Logger.ALogger logger = Logger.of(OnStartUp.class);

  @Inject
  public OnStartUp(Application application) {
    Nacaos.init();
    if (application.isDev()) {
      logger.info("dev模式");
    }
    if (application.isProd()) {
      logger.info("prod模式");
    }
    if (application.isTest()) {
      logger.info("test模式");
    }
  }
}
