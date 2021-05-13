package common.nacos.client;

import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author qingyun
 * @date 2021/5/11 2:04 下午
 */
public class NacosClient {

  private final WSClient wsClient;

  @Inject
  public NacosClient(@Named("nacos") WSClient wsClient) {
    this.wsClient = wsClient;
  }


  public WSRequest execute(String url) {
    return wsClient.url(url);
  }
}
