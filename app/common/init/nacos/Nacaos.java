package common.init.nacos;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import yangbajing.nacos4s.client.naming.Nacos4sNamingService;
import yangbajing.nacos4s.client.util.Nacos4s;

/**
 * @author qingyun
 * @date 2021/5/12 1:58 下午
 */
public class Nacaos {

  public static void init() {
    Config config = ConfigFactory.load().getConfig("nacos4s.client.naming");
    Nacos4sNamingService nacos4sNamingService = Nacos4s.namingService(config);
    nacos4sNamingService.autoRegisterInstance();
    nacos4sNamingService.getServerStatus();
  }
}
